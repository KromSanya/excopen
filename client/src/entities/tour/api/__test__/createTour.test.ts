import { describe, it, expect, beforeEach, afterEach, vi } from "vitest";
import { setupServer } from "msw/node";
import { http, HttpResponse } from "msw";
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
import {createTour} from "@/entities/tour/api";

describe("Create tour", () => {

    const PATH: string = "https://excopent.ru/api/tours"

    const mockTour: ITour = {
        id: 0,
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
        http.post(PATH, async ({request}) => {

            const tour = await request.json() as ITour

            if (tour.id === 0) {
                return HttpResponse.json({ message: "Tour created successfully" }, { status: 201 })
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

    it("Проверка о том, что экскурсия добавлена", async () => {
        await expect(createTour(mockTour)).resolves.toBeUndefined()
    })

    it("Проверка того, что данные корректы", async () => {
        server.use(
            http.post(PATH, async () => {
                return HttpResponse.json({ message: "Invalid data" }, { status: 400 })
            })
        )
        await expect(createTour({ ...mockTour, id: 1})).rejects.toThrow(ApiException)
    })

    it("Выбрасывает ApiException при ошибке сервера", async () => {
        server.use(
            http.post(PATH, async () => {
                return HttpResponse.json({ message: "Server error" }, { status: 500 })
            })
        )
        await expect(createTour(mockTour)).rejects.toThrow(ApiException)
    })

    it("Выбрасывает ошибку сети, если сервер недоступен", async () => {
        vi.spyOn(apiClient, "post").mockRejectedValue(new Error("Network Error"))
        await expect(createTour(mockTour)).rejects.toThrow("Network Error")
    })

})