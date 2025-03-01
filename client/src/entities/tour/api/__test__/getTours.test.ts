import { describe, it, expect, beforeAll, afterAll, vi } from "vitest";
import { setupServer } from "msw/node";
import { HttpResponse, http } from "msw";
import { getTours } from "@/entities/tour/api"; // Путь к вашей функции
import { ITour, SearchParamsType } from "@/shared/types";
import { apiClient, ApiException } from "@/shared/lib";

describe("Get tours", () => {

    const PATH: string = "https://excopent.ru/api/tours"

    const mockTours: ITour[] = [
        {
            id: 1,
            name: "Экскурсия по Москве",
            description: "Исторический маршрут по столице",
            duration: 3,
            price: 1500,
            availableSeats: 10,
            location: "Москва",
        },
        {
            id: 2,
            name: "Поездка в Петергоф",
            description: "Посещение дворцово-паркового ансамбля",
            duration: 5,
            price: 2000,
            availableSeats: 15,
            location: "Санкт-Петербург",
        },
    ];

    const server = setupServer(
        http.get(PATH, async ({ request }) => {

            const url = new URL(request.url)
            const location = url.searchParams.get("location")

            if (location === "Москва") {
                return HttpResponse.json(mockTours.filter(t => t.location === "Москва"), { status: 200 });
            }
            return HttpResponse.json(mockTours, { status: 200 })

        })
    )

    beforeAll(() => {
        server.listen()
    })

    afterAll(() => {
        server.close()
    })

    it("Успешное получение всех туров", async () => {
        const tours = await getTours(null)
        expect(tours).toEqual(mockTours)
    })

    it("Получение туров с фильтрацией по локации", async () => {
        const searchParams: SearchParamsType = { location: "Москва" }
        const filteredTours = await getTours(searchParams)
        expect(filteredTours).toEqual(mockTours.filter(t => t.location === "Москва"))
    })

    it("Выбрасывает ApiException при ошибке сервера", async () => {
        server.use(
            http.get(PATH, async () => {
                return HttpResponse.json({ message: "Server error" }, { status: 500 })
            })
        )
        await expect(getTours(null)).rejects.toThrow(ApiException)
    })

    it("Выбрасывает ошибку сети, если сервер недоступен", async () => {
        vi.spyOn(apiClient, "get").mockRejectedValue(new Error("Network Error"))
        await expect(getTours(null)).rejects.toThrow("Network Error")
    })

})