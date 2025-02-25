import {setupServer} from "msw/node";
import {http, HttpResponse} from "msw";
import {afterEach, beforeEach} from "vitest";
import {addToFav} from "@/entities";
import {apiClient, ApiException} from "@/shared/lib";

describe("Add to favourites", () => {

    const PATH: string = "https://excopent.ru/api/favorites/:tourId"

    const server = setupServer(
        http.post(PATH, async () => {
            return HttpResponse.json({}, {status: 200})
        })
    )

    beforeEach(() => {
        server.listen()
    })

    afterEach(() => {
        server.resetHandlers()
        server.close()
    })

    it("Успешное добавление экскурсии в избранное", async () => {
        await expect(addToFav(1)).resolves.toBeUndefined()
    })

    it("Проверка выброса ошибки типа ApiException", async () => {
        server.use(
            http.post(PATH, async () => {
                return HttpResponse.json({ message: "Server error" }, { status: 500 });
            })
        )
        await expect(addToFav(123)).rejects.toThrow(ApiException)
    })

    it("Проверка ошибки сети, если сервер недоступен", async () => {
        vi.spyOn(apiClient, "post").mockRejectedValue(new Error("Network Error"))
        await expect(addToFav(123)).rejects.toThrow("Network Error")
    })

})