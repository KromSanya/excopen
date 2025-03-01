import { describe, it, expect, beforeAll, afterAll, vi } from "vitest";
import { setupServer } from "msw/node";
import { HttpResponse, http } from "msw";
import { apiClient, ApiException } from "@/shared/lib";
import { getReview } from "@/entities/review/api";
import { IReview } from "@/shared/types";

describe("Get reviews", () => {

    const PATH = "https://excopent.ru/api/reviews"

    const mockReview: IReview = {
        id: 1,
        name: "Макс",
        rating: 4.5,
        positiveText: "Экскурсия была потрясающей! Гид рассказал много интересных фактов, маршрут удобный, виды просто шикарные.",
        negativeText: "Хотелось бы больше времени на фотосессии.",
        withChildren: true,
        personCount: 4
    }

    const server = setupServer(
        http.get(PATH, async ({ request }) => {

            const url = new URL(request.url)
            const id = url.searchParams.get("id")

            if (id === "1") return HttpResponse.json(mockReview, { status: 200 })
            return HttpResponse.json({ message: "Review not found" }, { status: 404 })

        })
    )

    beforeAll(() => {
        server.listen()
    })

    afterAll(() => {
        server.close()
    })

    it("Успешное получение отзыва", async () => {
        const data = await getReview(1)
        expect(data).toEqual(mockReview)
    })

    it("Возврат ошибки 404, если отзыв не найден", async () => {
        await expect(getReview(999)).rejects.toThrowError(ApiException)
    })

    it("Выбрасывает ApiException при ошибке сервера", async () => {
        server.use(
            http.get(PATH, async () => {
                return HttpResponse.json({ message: "Server error" }, { status: 500 })
            })
        )
        await expect(getReview(1)).rejects.toThrow(ApiException)
    })

    it("Выбрасывает ошибку сети, если сервер недоступен", async () => {
        vi.spyOn(apiClient, "get").mockRejectedValue(new Error("Network Error"))
        await expect(getReview(1)).rejects.toThrow("Network Error")
    })

})