import { render, screen } from "@testing-library/react";
import { describe, it, expect } from "vitest";
import {LocationCard} from "@/entities/location";

describe("Location card", () => {

    const mockProps = {
        country: "Россия",
        city: "Омск",
        tourCount: 52,
        image: "https://example.com/omsk.webp",
    }

    it("Проверка рендеринга компонента", () => {

        render(<LocationCard {...mockProps} />)

        expect(screen.getByText(mockProps.country)).toBeInTheDocument()
        expect(screen.getByText(mockProps.city)).toBeInTheDocument()
        expect(screen.getByText(`${mockProps.tourCount}+ экскурсий`)).toBeInTheDocument()

    })

    it("Проверка того, что переданный фон устанавливается", () => {
        render(<LocationCard {...mockProps} />)
        expect(screen.getByRole("locationCardBackground")).toHaveStyle(`background-image: url(${mockProps.image})`)
    })

    it("Скриншотный тест компонента с переданными пропсами", () => {
        const { container } = render(<LocationCard {...mockProps} />)
        expect(container).toMatchSnapshot()
    })

})