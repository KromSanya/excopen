import { describe, it, expect, beforeEach, afterEach, vi } from "vitest";
import { setupServer } from "msw/node";
import { http, HttpResponse } from "msw";
import { deleteReview } from "@/entities/review/api";
import {apiClient, ApiException} from "@/shared/lib";

describe("Delete review", () => {

    const PATH = "https://excopent.ru/api/reviews"

    const server = setupServer(
        http.delete(PATH, async ({ request }) => {

            const url = new URL(request.url)
            const id = url.searchParams.get("id")

            if (id === "1") {
                return HttpResponse.json({ message: "Review deleted successfully" }, { status: 200 })
            }
            return HttpResponse.json({ message: "Invalid review ID" }, { status: 400 })

        })
    )

    beforeEach(() => {
        server.listen()
    })

    afterEach(() => {
        server.resetHandlers()
        server.close()
    })

    it("Успешное удаление отзыва", async () => {
        await expect(deleteReview(1)).resolves.toBeUndefined()
    })

    it("Ошибка при удалении, если id некорректен", async () => {
        await expect(deleteReview(999)).rejects.toThrowError(ApiException)
    })

    it("Выбрасывает ApiException при ошибке сервера", async () => {
        server.use(
            http.delete(PATH, async () => {
                return HttpResponse.json({ message: "Server error" }, { status: 500 })
            })
        )
        await expect(deleteReview(1)).rejects.toThrowError(ApiException)
    })

    it("Выбрасывает ошибку сети, если сервер недоступен", async () => {
        vi.spyOn(apiClient, "delete").mockRejectedValue(new Error("Network Error"))
        await expect(deleteReview(1)).rejects.toThrow("Network Error")
    })

})