import { describe, it, expect, beforeAll, afterAll, vi } from "vitest";
import { setupServer } from "msw/node";
import { HttpResponse, http } from "msw";
import { apiClient, ApiException } from "@/shared/lib";
import { getReviewsByTourId } from "@/entities/review/api"; // Путь к вашей функции
import { IReview } from "@/shared/types";

describe("Get reviews by tour id", () => {

    const PATH = "https://excopent.ru/api/reviews/tour"

    const mockReviews: IReview[] = [
        {
            id: 1,
            name: "Макс",
            rating: 4.5,
            positiveText: "Экскурсия была потрясающей! Гид рассказал много интересных фактов, маршрут удобный, виды просто шикарные.",
            negativeText: "Хотелось бы больше времени на фотосессии.",
            withChildren: true,
            personCount: 4
        },
        {
            id: 2,
            name: "Оля",
            rating: 5,
            positiveText: "Очень понравилось! Прекрасный гид и маршрут.",
            negativeText: "Не было недостатков.",
            withChildren: false,
            personCount: 2
        }
    ]

    const server = setupServer(
        http.get(PATH, async ({ request }) => {

            const url = new URL(request.url)
            const tourId = url.searchParams.get("tourId")

            if (tourId === "1") return HttpResponse.json(mockReviews, { status: 200 })
            return HttpResponse.json([], { status: 200 })

        })
    )

    beforeAll(() => {
        server.listen()
    })

    afterAll(() => {
        server.close()
    })

    it("Успешное получение отзывов по id экскурсии", async () => {
        const reviews = await getReviewsByTourId(1)
        expect(reviews).toEqual(mockReviews)
    })

    it("Возврат пустого массива, если отзывы по ID тура не найдены", async () => {
        const reviews = await getReviewsByTourId(999)
        expect(reviews).toEqual([])
    })

    it("Выбрасывает ApiException при ошибке сервера", async () => {
        server.use(
            http.get(PATH, async () => {
                return HttpResponse.json({ message: "Server error" }, { status: 500 })
            })
        )
        await expect(getReviewsByTourId(10)).rejects.toThrow(ApiException)
    })

    it("Выбрасывает ошибку сети, если сервер недоступен", async () => {
        vi.spyOn(apiClient, "get").mockRejectedValue(new Error("Network Error"))
        await expect(getReviewsByTourId(1)).rejects.toThrow("Network Error")
    })

})