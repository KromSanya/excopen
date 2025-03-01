import { describe, it, expect, beforeEach, afterEach, vi } from "vitest";
import { setupServer } from "msw/node";
import { http, HttpResponse } from "msw";
import { apiClient, ApiException } from "@/shared/lib";
import {deleteFromFav} from "@/entities";

describe("Delete from favourites", () => {

    const PATH: string = "https://excopent.ru/api/favorites"

    const server = setupServer(
        http.delete(PATH, async ({ request }) => {

            const url = new URL(request.url)
            const tourId = url.searchParams.get("tourId")

            if (tourId === "1") return HttpResponse.json({}, { status: 200 })
            return HttpResponse.json({ message: "Not found" }, { status: 404 })

        })
    )

    beforeEach(() => {
        server.listen()
    })

    afterEach(() => {
        server.resetHandlers()
        server.close()
    })

    it("Успешное удаление тура из избранного", async () => {
        await expect(deleteFromFav(1)).resolves.toBeUndefined()
    })

    it("Выбрасывает ApiException при ошибке сервера", async () => {
        server.use(
            http.delete(PATH, async () => {
                return HttpResponse.json({ message: "Server error" }, { status: 500 })
            })
        )
        await expect(deleteFromFav(999)).rejects.toThrow(ApiException)
    })

    it("Выбрасывает ошибку сети, если сервер недоступен", async () => {
        vi.spyOn(apiClient, "delete").mockRejectedValue(new Error("Network Error"))
        await expect(deleteFromFav(789)).rejects.toThrow("Network Error")
    })

})