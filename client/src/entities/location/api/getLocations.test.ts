import {afterEach, beforeEach, describe, expect, it, vi} from "vitest";
import {ILocation} from "@/shared/types";
import {setupServer} from "msw/node";
import {http, HttpResponse} from "msw";
import {getLocations} from "@/entities";
import {apiClient, ApiException} from "@/shared/lib";

describe("Get locations", () => {

    const PATH: string = "https://excopent.ru/api/locations"

    const mockArray: ILocation[] = [
        {
            id: 1,
            city: "Москва",
            country: "Россия",
            tourCount: 120,
            region: "Московская область",
            image: "https://example.com/moscow.jpg"
        },
        {
            id: 2,
            city: "Париж",
            country: "Франция",
            tourCount: 95,
            region: "Иль-де-Франс",
            image: "https://example.com/paris.jpg"
        },
        {
            id: 3,
            city: "Нью-Йорк",
            country: "США",
            tourCount: 150,
            image: "https://example.com/newyork.jpg"
        }
    ]

    const server = setupServer(
        http.get(PATH, async () => {
            return HttpResponse.json(mockArray, {status: 200})
        })
    )

    beforeEach(() => {
        server.listen()
    })

    afterEach(() => {
        server.resetHandlers()
        server.close()
    })

    it("Успешное получение локаций, добавленных в Избранное", async () => {
        const data = await getLocations()
        expect(data).toEqual(mockArray)
    })

    it("Выбрасывает ApiException при ошибке сервера", async () => {
        server.use(
            http.get(PATH, async () => {
                return HttpResponse.json({ message: "Server error" }, { status: 500 })
            })
        )
        await expect(getLocations()).rejects.toThrow(ApiException)
    })

    it("Выбрасывает ошибку сети, если сервер недоступен", async () => {
        vi.spyOn(apiClient, "get").mockRejectedValue(new Error("Network Error"))
        await expect(getLocations()).rejects.toThrow("Network Error")
    })

})