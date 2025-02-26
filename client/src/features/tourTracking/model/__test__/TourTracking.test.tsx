import { beforeAll, describe, it, expect, vi } from "vitest";
import { render, screen } from "@testing-library/react";
import { TourTrackingProvider } from "@/app/providers/TourTrackingProvider.tsx";
import { TourTrackingContext } from "@/features";
import { ITour } from "@/shared/types";
import { ReactElement } from "react";
import userEvent from "@testing-library/user-event";
import {ContributorObject} from "@/shared/assets/tempData/ContributorObject.ts";
import {ContactsObject} from "@/shared/assets/tempData/ContactsObject.ts";
import {DescriptionObject} from "@/shared/assets/tempData/DescriptionObject.ts";

describe("TourTrackingContext", () => {

    function setup(jsx: ReactElement) {
        return {
            user: userEvent.setup(),
            ...render(jsx),
        }
    }

    const mockTour: ITour = {
        id: 0,
        title: "«Привокзалка»: место встречи изменить нельзя",
        shortDescription: "Изучить необычные достопримечательности микрорайона за ж/д вокзалом Омска!",
        images: [],
        map: "",
        price: 12800,
        priceForPerson: 4000,
        format: "Групповой",
        formatBehavior: "Пешком",
        groupCapacity: 10,
        contributor: ContributorObject,
        contact: ContactsObject,
        duration: "2 часа",
        routeLength: 2,
        rating: 5.0,
        ratingCount: 10,
        description: DescriptionObject,
        reviews: []
    }

    beforeAll(() => {
        vi.spyOn(global.localStorage, "getItem").mockImplementation(() => null)
        vi.spyOn(global.localStorage, "setItem").mockImplementation(() => {})
        vi.spyOn(global.localStorage, "removeItem").mockImplementation(() => {})
    })

    it("Проверка установки начального значения", (): void => {
        render(
            <TourTrackingProvider>
                <TourTrackingContext.Consumer>
                    {({ context }) => (
                        <div>
                            <span data-testid="viewed">{context.viewed.length}</span>
                            <span data-testid="favourites">{context.favourites.length}</span>
                        </div>
                    )}
                </TourTrackingContext.Consumer>
            </TourTrackingProvider>
        )

        expect(screen.getByTestId("viewed")).toHaveTextContent("0")
        expect(screen.getByTestId("favourites")).toHaveTextContent("0")
    })

    it("Проверка добавления в избранное", async (): Promise<void> => {

        const { user } = setup(
            <TourTrackingProvider>
                <TourTrackingContext.Consumer>
                    {({ context, addToFav }) => (
                        <div>
                            <button onClick={() => addToFav(mockTour)}>Добавить в избранное</button>
                            <span data-testid="favourites">{context.favourites.length}</span>
                        </div>
                    )}
                </TourTrackingContext.Consumer>
            </TourTrackingProvider>
        )

        await user.click(screen.getByText("Добавить в избранное"))
        expect(screen.getByTestId("favourites")).toHaveTextContent("1")

    })

    it("Проверка удаления из избранного", async (): Promise<void> => {

        const { user } = setup(
            <TourTrackingProvider>
                <TourTrackingContext.Consumer>
                    {({ context, addToFav, deleteFromFav }) => (
                        <div>
                            <button onClick={() => addToFav(mockTour)}>Добавить в избранное</button>
                            <button onClick={() => deleteFromFav(mockTour)}>Удалить из избранного</button>
                            <span data-testid="favourites">{context.favourites.length}</span>
                        </div>
                    )}
                </TourTrackingContext.Consumer>
            </TourTrackingProvider>
        )

        await user.click(screen.getByText("Добавить в избранное"))
        expect(screen.getByTestId("favourites")).toHaveTextContent("1")

        await user.click(screen.getByText("Удалить из избранного"))
        expect(screen.getByTestId("favourites")).toHaveTextContent("0")

    })

    it("Проверка добавления в просмотренные", async (): Promise<void> => {

        const { user } = setup(
            <TourTrackingProvider>
                <TourTrackingContext.Consumer>
                    {({ context, addToViewed }) => (
                        <div>
                            <button onClick={() => addToViewed(mockTour)}>Добавить в просмотренные</button>
                            <span data-testid="viewed">{context.viewed.length}</span>
                        </div>
                    )}
                </TourTrackingContext.Consumer>
            </TourTrackingProvider>
        )

        await user.click(screen.getByText("Добавить в просмотренные"))
        expect(screen.getByTestId("viewed")).toHaveTextContent("1")

    })

})