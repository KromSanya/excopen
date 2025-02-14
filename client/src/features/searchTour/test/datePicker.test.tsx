import {TourAccessibility} from "@/shared/types";
import {describe} from "vitest";
import {render, screen} from "@testing-library/react";
import {BrowserRouter} from "react-router-dom";
import {SearchContext} from "@/features/searchTour/model/context/context.ts";
import {DatePicker} from "@/features/searchTour/ui/form/datePicker"
import {userEvent} from "@testing-library/user-event";

describe("Form DatePicker", () => {

    function setup(jsx: any) {
        return {
            user: userEvent.setup(),
            ...render(jsx),
        }
    }

    vi.mock("usehooks-ts", () => ({
        useOnClickOutside: vi.fn(),
    }))

    const mockSearchParams = {
        searchParams: {
            location: "",
            date: { from: undefined, to: undefined },
            accessibility: TourAccessibility.WITHOUT_CHILDREN,
            byCity: false,
        },
        isSearch: false,
        setLocation: vi.fn(),
        setAccessibility: vi.fn(),
        setByCity: vi.fn(),
        setDate: vi.fn(),
        setIsSearch: vi.fn(),
    }

    it("Проверка рендеринга компонента", () => {

        render(
            <BrowserRouter>
                <SearchContext.Provider value={mockSearchParams}>
                    <DatePicker />
                </SearchContext.Provider>
            </BrowserRouter>
        )

        expect(screen.getByRole("form-datePicker")).toBeInTheDocument()

    })

    it("Проверка открытия календаря", async () => {

        const {user} = setup(
            <BrowserRouter>
                <SearchContext.Provider value={mockSearchParams}>
                    <DatePicker/>
                </SearchContext.Provider>
            </BrowserRouter>
        )

        await user.click(screen.getByRole("form-datePicker"))

        expect(screen.getByRole("dialog")).toBeInTheDocument()

    })

    it("Проверка выбора даты", async () => {

        const {user} = setup(
            <BrowserRouter>
                <SearchContext.Provider value={mockSearchParams}>
                    <DatePicker />
                </SearchContext.Provider>
            </BrowserRouter>
        )

        await user.click(screen.getByRole("form-datePicker"))
        await user.click(screen.getByText("Когда"))

        expect(mockSearchParams.setDate).toHaveBeenCalled()

    })

})