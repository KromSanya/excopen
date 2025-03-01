import { describe, it, expect } from "vitest"
import { render, screen } from "@testing-library/react"
import {Header} from "@/entities/review/ui/header"

describe("Review header", () => {

    const mockProps = {
        rating: 4.5,
        ratingCount: 44,
    }

    it("Проверка рендеринга", () => {
        render(<Header {...mockProps} />)
    })

    it("Snapshot тест", () => {
        const { container } = render(<Header {...mockProps} />)
        expect(container).toMatchSnapshot()
    })

    it("Проверка отображения количества отзывов", () => {
        render(<Header {...mockProps} />)
        expect(screen.getByText("44 отзыва путешественников")).toBeInTheDocument()
    })

})