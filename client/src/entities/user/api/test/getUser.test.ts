import { describe, it, expect, beforeAll, afterAll, vi } from "vitest";
import { setupServer } from "msw/node";
import { HttpResponse, http } from "msw";
import { apiClient, ApiException } from "@/shared/lib";
import {IUser} from "@/shared/types";
import {getUser} from "@/entities";

describe("Get user", () => {

    const PATH = "https://excopent.ru/api/users"

    const mockUser: IUser = {
        id: 1,
        name: "Ivan",
        surname: "Ivanov",
        email: "ivanov@mail.ru",
        orders: []
    }

    const server = setupServer(
        http.get(PATH, async ({ request }) => {

            const url = new URL(request.url)
            const id = url.searchParams.get("id")

            if (id === "1") return HttpResponse.json(mockUser, { status: 200 })
            return HttpResponse.json({ message: "Review not found" }, { status: 404 })

        })
    )

    beforeAll(() => {
        server.listen()
    })

    afterAll(() => {
        server.close()
    })

    it("Успешное получение пользователя", async () => {
        const data = await getUser(1)
        expect(data).toEqual(mockUser)
    })

    it("Возврат ошибки 404, если пользователь не найден", async () => {
        await expect(getUser(999)).rejects.toThrowError(ApiException)
    })

    it("Выбрасывает ApiException при ошибке сервера", async () => {
        server.use(
            http.get(PATH, async () => {
                return HttpResponse.json({ message: "Server error" }, { status: 500 })
            })
        )
        await expect(getUser(1)).rejects.toThrow(ApiException)
    })

    it("Выбрасывает ошибку сети, если сервер недоступен", async () => {
        vi.spyOn(apiClient, "get").mockRejectedValue(new Error("Network Error"))
        await expect(getUser(1)).rejects.toThrow("Network Error")
    })

})