import { render, screen } from "@testing-library/react"
import { Form } from "@/entities/review/ui/form"

describe("Компонент формы отзыва", () => {

    it("Компонент должен рендериться без ошибок", () => {
        render(<Form/>)
        expect(screen.getByText("Расскажите о ваших впечатлениях")).toBeInTheDocument()
    })

    it("Snapshot тест", () => {
        const { container } = render(<Form/>)
        expect(container).toMatchSnapshot()
    })

    it("Кнопка 'Добавить' должна быть отключена, если не все поля заполнены", () => {
        render(<Form/>)
        expect(screen.getByText("Добавить")).toBeDisabled()
    })

})