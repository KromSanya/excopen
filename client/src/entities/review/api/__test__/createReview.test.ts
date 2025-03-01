import { describe, it, expect, beforeEach, afterEach, vi } from "vitest";
import { setupServer } from "msw/node";
import { http, HttpResponse } from "msw";
import { apiClient, ApiException } from "@/shared/lib";
import { IReview } from "@/shared/types";
import {createReview} from "@/entities/review/api";

describe("Create review", () => {

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
        http.post(PATH, async ({request}) => {

            const review = await request.json() as IReview

            if (review.name === "Макс" && review.rating === 4.5) {
                return HttpResponse.json({ message: "Review created successfully" }, { status: 201 })
            }
            return HttpResponse.json({ message: "Invalid data" }, { status: 400 })

        })
    )

    beforeEach(() => {
        server.listen()
    })

    afterEach(() => {
        server.resetHandlers()
        server.close()
    })

    it("Проверка о том, что отзыв добавлен", async () => {
        await expect(createReview(mockReview)).resolves.toBeUndefined()
    })

    it("Проверка того, что данные корректы", async () => {
        server.use(
            http.post(PATH, async () => {
                return HttpResponse.json({ message: "Invalid data" }, { status: 400 })
            })
        )
        await expect(createReview({ ...mockReview, name: "Олег" })).rejects.toThrow(ApiException)
    })

    it("Выбрасывает ApiException при ошибке сервера", async () => {
        server.use(
            http.post(PATH, async () => {
                return HttpResponse.json({ message: "Server error" }, { status: 500 })
            })
        )
        await expect(createReview(mockReview)).rejects.toThrow(ApiException)
    })

    it("Выбрасывает ошибку сети, если сервер недоступен", async () => {
        vi.spyOn(apiClient, "post").mockRejectedValue(new Error("Network Error"))
        await expect(createReview(mockReview)).rejects.toThrow("Network Error")
    })

})