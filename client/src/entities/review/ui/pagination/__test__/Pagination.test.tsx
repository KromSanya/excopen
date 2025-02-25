import { describe, it, expect, vi } from "vitest"
import { render, screen } from "@testing-library/react"
import {Pagination} from "@/entities/review/ui/pagination"
import {userEvent} from "@testing-library/user-event";

describe("Pagination component", () => {

    const mockProps = {
        visibleReviews: 2,
        setVisibleReviews: vi.fn(),
        reviews: Array(5).fill({}),
    }

    it("Компонент должен рендериться", () => {
        render(<Pagination {...mockProps} />)
    })

    it("Snapshot тест", () => {
        const { container } = render(<Pagination {...mockProps} />)
        expect(container).toMatchSnapshot()
    })

    it("Кнопка 'Загрузить еще' должна отображаться, если есть дополнительные отзывы для загрузки", () => {
        render(<Pagination {...mockProps} />)
        expect(screen.getByText("Загрузить еще")).toBeInTheDocument()
    })

    it("Кнопка 'Скрыть' не должна отображаться, если visibleReviews равно 2", () => {
        render(<Pagination {...mockProps} />)
        expect(screen.queryByText("Скрыть")).not.toBeInTheDocument()
    })

    it("Функция setVisibleReviews должна быть вызвана с параметром +2, когда нажата кнопка 'Загрузить еще'", async () => {
        render(<Pagination {...mockProps} />)
        await userEvent.click(screen.getByText("Загрузить еще"))
        expect(mockProps.setVisibleReviews).toHaveBeenCalledWith(expect.any(Function))
    })

    it("Кнопка 'Скрыть' должна отображаться, если visibleReviews больше 2", () => {
        render(<Pagination {...mockProps} visibleReviews={4} />)
        expect(screen.getByText("Скрыть")).toBeInTheDocument()
    })

    it("Функция setVisibleReviews должна быть вызвана с параметром -2, когда нажата кнопка 'Скрыть'", async () => {
        render(<Pagination {...mockProps} visibleReviews={4} />)
        await userEvent.click(screen.getByText("Скрыть"))
        expect(mockProps.setVisibleReviews).toHaveBeenCalledWith(expect.any(Function))
    })

})