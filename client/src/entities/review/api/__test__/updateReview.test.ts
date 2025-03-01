import { describe, it, expect, vi, beforeAll, afterAll } from "vitest";
import { setupServer } from "msw/node";
import { HttpResponse, http } from "msw";
import { updateReview } from "@/entities/review/api";
import { IReview } from "@/shared/types";
import { apiClient, ApiException } from "@/shared/lib";

describe("Update review", () => {

    const PATH: string = "https://excopent.ru/api/reviews"

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
        http.put(PATH, async ({ request }) => {

            const updatedReview = await request.json() as IReview

            if (updatedReview.id === 1) return HttpResponse.json(updatedReview, { status: 200 })
            return HttpResponse.json({ message: "Invalid review ID" }, { status: 400 })

        })
    )

    beforeAll(() => {
        server.listen()
    })

    afterAll(() => {
        server.close()
    })

    it("Успешное обновление отзыва", async () => {
        const updatedReview = await updateReview(mockReview)
        expect(updatedReview).toEqual(mockReview)
    })

    it("Возврат ошибки, если отзыв с таким ID не найден", async () => {
        const invalidReview: IReview = { ...mockReview, id: 999 }
        await expect(updateReview(invalidReview)).rejects.toThrowError(ApiException)
    })

    it("Выбрасывает ошибку сервера (500)", async () => {
        server.use(
            http.put(PATH, async () => {
                return HttpResponse.json({ message: "Server error" }, { status: 500 })
            })
        )
        await expect(updateReview(mockReview)).rejects.toThrowError(ApiException)
    })

    it("Выбрасывает ошибку сети, если сервер недоступен", async () => {
        vi.spyOn(apiClient, "put").mockRejectedValue(new Error("Network Error"))
        await expect(updateReview(mockReview)).rejects.toThrowError("Network Error")
    })

})