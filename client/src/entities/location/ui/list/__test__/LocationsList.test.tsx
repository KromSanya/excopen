import { describe, it } from "vitest";
import { render, screen } from "@testing-library/react";
import { MemoryRouter } from "react-router-dom";
import {Locations} from "@/entities"

describe("Locations list", () => {

    it("Проверка монтирования компонента", () => {
        render(
            <MemoryRouter>
                <Locations/>
            </MemoryRouter>
        )
    })

    it("Snapshot тест", () => {
        const { container } = render(
            <MemoryRouter>
                <Locations/>
            </MemoryRouter>
        )
        expect(container).toMatchSnapshot()
    })

    it("Проверка наличия заголовка в компоненте", () => {
        render(
            <MemoryRouter>
                <Locations />
            </MemoryRouter>
        )
        expect(screen.getByRole("heading", { level: 2 })).toHaveTextContent("Самые популярные направления")
    })

    it("Проверка наличия кнопки в компоненте", () => {
        render(
            <MemoryRouter initialEntries={["/"]}>
                <Locations />
            </MemoryRouter>
        )
        expect(screen.getByRole("button", { name: "перейти к списку городов" })).toBeInTheDocument()
    })

})