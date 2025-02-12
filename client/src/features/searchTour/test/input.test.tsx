import {describe, expect, vi} from "vitest";
import {fireEvent, render, screen} from "@testing-library/react";
import {BrowserRouter} from "react-router-dom";
import {SearchContext} from "@/features/searchTour/model/context/context.ts";
import {Input} from "@/features/searchTour/ui/form/input"
import {TourAccessibility} from "@/shared/types";

describe("Form input", () => {

    vi.mock("usehooks-ts", () => ({
        useOnClickOutside: vi.fn()
    }))

    const mockSearchParams = {
        searchParams: {
            location: "",
            date: { from: undefined, to: undefined },
            accessibility: TourAccessibility.WITHOUT_CHILDREN,
            byCity: false
        },
        isSearch: false,
        setLocation: vi.fn(),
        setAccessibility: vi.fn(),
        setByCity: vi.fn(),
        setDate: vi.fn(),
        setIsSearch: vi.fn()
    }

    it("Проверка рендеринга", () => {

        render(
            <BrowserRouter>
                <SearchContext.Provider value={mockSearchParams}>
                    <Input/>
                </SearchContext.Provider>
            </BrowserRouter>
        )

        expect(screen.getByRole("form-command")).toBeInTheDocument()
        expect(screen.getByText("Где искать")).toBeInTheDocument()

    })

    it("Проверка ввода текста", () => {

        render(
            <BrowserRouter>
                <SearchContext.Provider value={mockSearchParams}>
                    <Input/>
                </SearchContext.Provider>
            </BrowserRouter>
        )

        fireEvent.change(screen.getByRole("combobox"), { target: { value: "Омск" } })
        expect(mockSearchParams.setLocation).toHaveBeenCalledWith("Омск")

    })

    it("Проверка открытия выпадающего списка", () => {

        render(
            <BrowserRouter>
                <SearchContext.Provider value={mockSearchParams}>
                    <Input />
                </SearchContext.Provider>
            </BrowserRouter>
        )

        fireEvent.focus(screen.getByRole("form-command"))
        expect(screen.getByRole("listbox")).toBeInTheDocument()

    })

    it("Выбор локации из выпадающего списка", () => {

        render(
            <BrowserRouter>
                <SearchContext.Provider value={mockSearchParams}>
                    <Input />
                </SearchContext.Provider>
            </BrowserRouter>
        )

        fireEvent.focus(screen.getByRole("form-command"))
        fireEvent.click(screen.getAllByText("Москва")[0])

        expect(mockSearchParams.setLocation).toHaveBeenCalledWith("Москва")

    })

    it("Валидация значения", () => {

        render(
            <BrowserRouter>
                <SearchContext.Provider value={mockSearchParams}>
                    <Input />
                </SearchContext.Provider>
            </BrowserRouter>
        )

        fireEvent.change(screen.getByRole("combobox"), {
            target: {
                value: "абв"
            }
        })

        expect(mockSearchParams.setLocation).toHaveBeenCalledWith("абв")
        expect(screen.getByRole("combobox")).toHaveAttribute("data-is-corrected", "false")

    })

})