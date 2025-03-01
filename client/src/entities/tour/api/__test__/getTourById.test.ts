import { describe, it, expect, beforeAll, afterAll, vi } from "vitest";
import { setupServer } from "msw/node";
import { HttpResponse, http } from "msw";
import { apiClient, ApiException } from "@/shared/lib";
import {ITour} from "@/shared/types";
import image from "@/shared/assets/tempData/assets/img1.png";
import image2 from "@/shared/assets/tempData/assets/img2.png";
import image3 from "@/shared/assets/tempData/assets/img3.png";
import image4 from "@/shared/assets/tempData/assets/img4.png";
import map from "@/shared/assets/tempData/assets/map.png";
import {ContributorObject} from "@/shared/assets/tempData/ContributorObject.ts";
import {ContactsObject} from "@/shared/assets/tempData/ContactsObject.ts";
import {DescriptionObject} from "@/shared/assets/tempData/DescriptionObject.ts";
import {getTourById} from "@/entities/tour/api";

describe("Get tour by id", () => {

    const PATH = "https://excopent.ru/api/tours/tour"

    const mockTour: ITour = {
        id: 1,
        title: "«Привокзалка»: место встречи изменить нельзя",
        shortDescription: "Изучить необычные достопримечательности микрорайона за ж/д вокзалом Омска!",
        images: [image, image2, image3, image4, image3],
        map: map,
        price: 12800,
        priceForPerson: 4000,
        format: "Групповой",
        formatBehavior: "Пешком",
        groupCapacity: 10,
        contributor: ContributorObject,
        contact: ContactsObject,
        duration: "2 часа",
        routeLength: 2,
        rating: 5.0,
        ratingCount: 10,
        description: DescriptionObject,
        reviews: []
    }

    const server = setupServer(
        http.get(PATH, async ({ request }) => {

            const url = new URL(request.url)
            const id = url.searchParams.get("id")

            if (id === "1") return HttpResponse.json(mockTour, { status: 200 })
            return HttpResponse.json({ message: "Review not found" }, { status: 404 })

        })
    )

    beforeAll(() => {
        server.listen()
    })

    afterAll(() => {
        server.close()
    })

    it("Успешное получение экскурсии", async () => {
        const data = await getTourById(1)
        expect(data).toEqual(mockTour)
    })

    it("Возврат ошибки 404, если экскурсия не найден", async () => {
        await expect(getTourById(999)).rejects.toThrowError(ApiException)
    })

    it("Выбрасывает ApiException при ошибке сервера", async () => {
        server.use(
            http.get(PATH, async () => {
                return HttpResponse.json({ message: "Server error" }, { status: 500 })
            })
        )
        await expect(getTourById(1)).rejects.toThrow(ApiException)
    })

    it("Выбрасывает ошибку сети, если сервер недоступен", async () => {
        vi.spyOn(apiClient, "get").mockRejectedValue(new Error("Network Error"))
        await expect(getTourById(1)).rejects.toThrow("Network Error")
    })

})