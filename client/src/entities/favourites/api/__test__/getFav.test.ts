import { describe, it, expect, beforeEach, afterEach, vi } from "vitest";
import { setupServer } from "msw/node";
import { http, HttpResponse } from "msw";
import { getFav } from "@/entities";
import { apiClient, ApiException } from "@/shared/lib";
import {IFavourite} from "@/shared/types";

describe("Get favourites", () => {

    const PATH: string = "https://excopent.ru/api/favorites"

    const mockArray: IFavourite[] = [
        { userId: 1, tourId: 10 },
        { userId: 1, tourId: 20 },
    ]

    const server = setupServer(
        http.get(PATH, async ({ request }) => {

            const url = new URL(request.url)
            const userId = url.searchParams.get("userId")

            if (userId === "1") return HttpResponse.json(mockArray, { status: 200 })
            return HttpResponse.json([], { status: 200 })

        })
    )

    beforeEach(() => {
        server.listen()
    })

    afterEach(() => {
        server.resetHandlers()
        server.close()
    })

    it("Успешное получение экскурсий, добавленных в Избранное", async () => {
        const data = await getFav(1)
        expect(data).toEqual(mockArray)
    })

    it("Возврат пустого массива, есть у пользователя нет экскурсий в Избранном", async () => {
        const data = await getFav(999)
        expect(data).toEqual([])
    })

    it("Выбрасывает ApiException при ошибке сервера", async () => {
        server.use(
            http.get(PATH, async () => {
                return HttpResponse.json({ message: "Server error" }, { status: 500 })
            })
        )
        await expect(getFav(123)).rejects.toThrow(ApiException)
    })

    it("Выбрасывает ошибку сети, если сервер недоступен", async () => {
        vi.spyOn(apiClient, "get").mockRejectedValue(new Error("Network Error"))
        await expect(getFav(123)).rejects.toThrow("Network Error")
    })

})