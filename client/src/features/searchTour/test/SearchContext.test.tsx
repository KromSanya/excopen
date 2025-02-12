import {beforeAll, describe} from "vitest";
import {render, screen} from "@testing-library/react";
import {SearchProvider} from "@/features";
import { SearchContext } from "../model/context/context";
import {TourAccessibility} from "@/shared/types";
import {userEvent} from "@testing-library/user-event";

describe("SearchContext", () => {

    function setup(jsx: any) {
        return {
            user: userEvent.setup(),
            ...render(jsx),
        }
    }

    beforeAll(() => {
        vi.spyOn(global.localStorage, 'getItem').mockImplementation(() => null);
        vi.spyOn(global.localStorage, 'setItem').mockImplementation(() => {});
        vi.spyOn(global.localStorage, 'removeItem').mockImplementation(() => {});
    })

    it("Проверка установки начального значения", () => {

        render(
            <SearchProvider>
                <SearchContext.Consumer>
                    {({ searchParams }) => (
                        <div>
                            <span data-testid="location">{searchParams.location}</span>
                            <span data-testid="accessibility">{searchParams.accessibility}</span>
                            <span data-testid="byCity">{searchParams.byCity ? 'true' : 'false'}</span>
                        </div>
                    )}
                </SearchContext.Consumer>
            </SearchProvider>
        )

        expect(screen.getByTestId('location')).toHaveTextContent('')
        expect(screen.getByTestId('accessibility')).toHaveTextContent('')
        expect(screen.getByTestId('byCity')).toHaveTextContent('false')

    })

    it("Проверка обновления location", async () => {

        const {user} = setup(
            <SearchProvider>
                <SearchContext.Consumer>
                    {({ searchParams, setLocation }) => (
                        <div>
                            <button onClick={() => setLocation("Омск")}>Set Location</button>
                            <span data-testid="location">{searchParams.location}</span>
                        </div>
                    )}
                </SearchContext.Consumer>
            </SearchProvider>
        )

        await user.click(screen.getByText("Set Location"))
        expect(screen.getByTestId("location")).toHaveTextContent("Омск")

    })

    it("Проверка обновления accessibility", async () => {

        const {user} = setup(
            <SearchProvider>
                <SearchContext.Consumer>
                    {({ searchParams, setAccessibility }) => (
                        <div>
                            <button onClick={() => setAccessibility(TourAccessibility.WITH_CHILDREN)}>Set accessibility</button>
                            <span data-testid="accessibility">{searchParams.accessibility}</span>
                        </div>
                    )}
                </SearchContext.Consumer>
            </SearchProvider>
        )

        await user.click(screen.getByText("Set accessibility"))
        expect(screen.getByTestId("accessibility")).toHaveTextContent(TourAccessibility.WITH_CHILDREN)

    })

})