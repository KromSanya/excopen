import { describe, it, expect } from "vitest";
import { render, screen } from "@testing-library/react";
import {Card} from "@/entities/review/ui/card"

describe("Review card", () => {

    const mockProps = {
        name: "Иван",
        rating: 4,
        positiveText: "Отличный сервис",
        negativeText: "Долгое ожидание",
        withChildren: true,
        personCount: 2,
    }

    it("Проверка рендеринга компонента", () => {
        render(<Card {...mockProps} />)
    })

    it("Snapshot тест", () => {
        const { container } = render(<Card {...mockProps} />)
        expect(container).toMatchSnapshot()
    })

    it("Проверка отображения имени в отзыве", () => {
        render(<Card {...mockProps} />)
        expect(screen.getByText("Иван")).toBeInTheDocument()
    })

    it("Проверка отображения доступности в отзыве", () => {
        render(<Card {...mockProps} />)
        expect(screen.getByText("Путешествовал(а) с детьми,2 персоны")).toBeInTheDocument()
    })

    it("Проверка отображения корректного рейтинг", () => {
        render(<Card {...mockProps} />)
        expect(screen.getByText("4")).toBeInTheDocument()
    })

    it("Проверка отображения негативного и положителного комментариев", () => {
        render(<Card {...mockProps} />);
        expect(screen.getByText("Отличный сервис")).toBeInTheDocument()
        expect(screen.getByText("Долгое ожидание")).toBeInTheDocument()
    })

    it("Отображения корректного класса с рейтингом <= 3", () => {
        render(<Card {...mockProps} rating={3} />)
        expect(screen.getByText("3")).toHaveClass("bg-red-gr")
    })

    it("Отображения корректного класса с рейтингом > 3", () => {
        render(<Card {...mockProps} rating={4} />)
        expect(screen.getByText("4")).toHaveClass("bg-green-gr")
    })

})