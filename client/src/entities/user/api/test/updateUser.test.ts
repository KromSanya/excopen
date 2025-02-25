import { describe, it, expect, vi, beforeAll, afterAll } from "vitest";
import { setupServer } from "msw/node";
import { HttpResponse, http } from "msw";
import {IUser} from "@/shared/types";
import { apiClient, ApiException } from "@/shared/lib";
import {updateUser} from "@/entities";

describe("Update user", () => {

    const PATH: string = "https://excopent.ru/api/users"

    const mockUser: IUser = {
        id: 1,
        name: "Ivan",
        surname: "Ivanov",
        email: "ivanov@mail.ru",
        orders: []
    }

    const server = setupServer(
        http.put(PATH, async ({ request }) => {

            const updatedUser = await request.json() as IUser

            if (updatedUser.id === 1) return HttpResponse.json(updatedUser, { status: 200 })
            return HttpResponse.json({ message: "Invalid tour id" }, { status: 400 })

        })
    )

    beforeAll(() => {
        server.listen()
    })

    afterAll(() => {
        server.close()
    })

    it("Успешное обновление пользователя", async () => {
        const updatedUser = await updateUser(mockUser)
        expect(updatedUser).toEqual(mockUser)
    })

    it("Возврат ошибки, если пользователь с таким id не найден", async () => {
        const invalidUser: IUser = { ...mockUser, id: 999 }
        await expect(updateUser(invalidUser)).rejects.toThrowError(ApiException)
    })

    it("Выбрасывает ошибку сервера (500)", async () => {
        server.use(
            http.put(PATH, async () => {
                return HttpResponse.json({ message: "Server error" }, { status: 500 })
            })
        )
        await expect(updateUser(mockUser)).rejects.toThrowError(ApiException)
    })

    it("Выбрасывает ошибку сети, если сервер недоступен", async () => {
        vi.spyOn(apiClient, "put").mockRejectedValue(new Error("Network Error"))
        await expect(updateUser(mockUser)).rejects.toThrowError("Network Error")
    })

})