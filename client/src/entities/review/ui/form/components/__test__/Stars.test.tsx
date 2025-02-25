import { render } from "@testing-library/react";
import { vi } from "vitest";
import {Stars} from "@/entities/review/ui/form/components/index.ts";

describe("Stars Component", () => {

    const mockSetRating = vi.fn()

    it("Компонент должен рендериться", () => {
        render(<Stars rating={3} setRating={mockSetRating} />)
    })

    it("Компонент должен соответствовать снимку", () => {
        const { container } = render(<Stars rating={3} setRating={mockSetRating} />)
        expect(container).toMatchSnapshot()
    })

})