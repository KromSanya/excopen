import {Orientation, TourAccessibility} from "@/shared/types";
import {describe} from "vitest";
import {BrowserRouter} from "react-router-dom";
import {render, screen} from "@testing-library/react";
import {SearchContext} from "@/features/searchTour/model/context/context.ts";
import {SearchButton} from "@/features/searchTour/ui/form/button"
import {userEvent} from "@testing-library/user-event";
import {SearchContextType} from "@/features/searchTour/model/context/types.ts";
import {ReactElement} from "react";

describe("Form button", () => {

    const mockNavigate = vi.fn()

    vi.mock("usehooks-ts", () => ({
        useOnClickOutside: vi.fn(),
    }))

    const mockSearchParams: SearchContextType = {
        context: {
            searchParams: {
                location: "",
                date: { from: undefined, to: undefined },
                accessibility: TourAccessibility.WITHOUT_CHILDREN,
                byCity: false,
            },
            isSearch: false
        },
        setLocation: vi.fn(),
        setAccessibility: vi.fn(),
        setByCity: vi.fn(),
        setDate: vi.fn(),
        setIsSearch: vi.fn(),
    }

    function setup(jsx: ReactElement) {
        return {
            user: userEvent.setup(),
            ...render(jsx),
        }
    }

    it("Проверка рендеринга кнопки", () => {

        render(
            <BrowserRouter>
                <SearchContext.Provider value={mockSearchParams}>
                    <SearchButton location={"Москва"} disabled={false} orientation={Orientation.HORIZONTAL}/>
                </SearchContext.Provider>
            </BrowserRouter>
        )

        expect(screen.getByRole("searchButton")).toBeInTheDocument()

    })

    it("Проверка клика по кнопке, если форма не заполнена", async () => {

        const {user} = setup(
            <BrowserRouter>
                <SearchContext.Provider value={mockSearchParams}>
                    <SearchButton location={"Москва"} disabled={false} orientation={Orientation.HORIZONTAL} />
                </SearchContext.Provider>
            </BrowserRouter>
        )

        await user.click(screen.getByRole("searchButton"))

        expect(mockSearchParams.setIsSearch).toHaveBeenCalled()
        expect(mockNavigate).not.toHaveBeenCalled()

    })

    it("Проверка клика по кнопке, если форма заполнена", async () => {

        const {user} = setup(
            <BrowserRouter>
                <SearchContext.Provider value={mockSearchParams}>
                    <SearchButton location={"Москва"} disabled={true} orientation={Orientation.HORIZONTAL} />
                </SearchContext.Provider>
            </BrowserRouter>
        )

        await user.click(screen.getByRole("searchButton"))
        expect(mockSearchParams.setIsSearch).toHaveBeenCalledWith(true)

    })

})