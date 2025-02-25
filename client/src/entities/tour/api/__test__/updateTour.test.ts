import { describe, it, expect, vi, beforeAll, afterAll } from "vitest";
import { setupServer } from "msw/node";
import { HttpResponse, http } from "msw";
import {ITour} from "@/shared/types";
import { apiClient, ApiException } from "@/shared/lib";
import image from "@/shared/assets/tempData/assets/img1.png";
import image2 from "@/shared/assets/tempData/assets/img2.png";
import image3 from "@/shared/assets/tempData/assets/img3.png";
import image4 from "@/shared/assets/tempData/assets/img4.png";
import map from "@/shared/assets/tempData/assets/map.png";
import {ContributorObject} from "@/shared/assets/tempData/ContributorObject.ts";
import {ContactsObject} from "@/shared/assets/tempData/ContactsObject.ts";
import {DescriptionObject} from "@/shared/assets/tempData/DescriptionObject.ts";
import {updateTour} from "@/entities/tour/api";

describe("Update tour", () => {

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
        http.put(PATH, async ({ request }) => {

            const updatedTour = await request.json() as ITour

            if (updatedTour.id === 0) return HttpResponse.json(updatedTour, { status: 200 })
            return HttpResponse.json({ message: "Invalid tour id" }, { status: 400 })

        })
    )

    beforeAll(() => {
        server.listen()
    })

    afterAll(() => {
        server.close()
    })

    it("Успешное обновление экскурсии", async () => {
        const updatedTour = await updateTour(mockTour)
        expect(updatedTour).toEqual(mockTour)
    })

    it("Возврат ошибки, если экскурсии с таким id не найдена", async () => {
        const invalidTour: ITour = { ...mockTour, id: 999 }
        await expect(updateTour(invalidTour)).rejects.toThrowError(ApiException)
    })

    it("Выбрасывает ошибку сервера (500)", async () => {
        server.use(
            http.put(PATH, async () => {
                return HttpResponse.json({ message: "Server error" }, { status: 500 })
            })
        )
        await expect(updateTour(mockTour)).rejects.toThrowError(ApiException)
    })

    it("Выбрасывает ошибку сети, если сервер недоступен", async () => {
        vi.spyOn(apiClient, "put").mockRejectedValue(new Error("Network Error"))
        await expect(updateTour(mockTour)).rejects.toThrowError("Network Error")
    })

})