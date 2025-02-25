import { render, screen } from "@testing-library/react";
import {describe, it, expect, vi, MockedFunction} from "vitest";
import {TourTrackingContextType, useTourTrackingContext} from "@/features";
import {ToFavourite} from "@/entities/favourites"
import { ITour } from "@/shared/types";
import image from "@/shared/assets/tempData/assets/img1.png";
import image2 from "@/shared/assets/tempData/assets/img2.png";
import image3 from "@/shared/assets/tempData/assets/img3.png";
import image4 from "@/shared/assets/tempData/assets/img4.png";
import map from "@/shared/assets/tempData/assets/map.png";
import {ContributorObject} from "@/shared/assets/tempData/ContributorObject.ts";
import {ContactsObject} from "@/shared/assets/tempData/ContactsObject.ts";
import {DescriptionObject} from "@/shared/assets/tempData/DescriptionObject.ts";
import {userEvent} from "@testing-library/user-event";

describe("To favourite button", () => {

    vi.mock("@/features", () => ({
        useTourTrackingContext: vi.fn(),
    }))

    const addToFavMock = vi.fn()
    const deleteFromFavMock = vi.fn()

    const mockTour: ITour = {
        id: 0,
        title: "«Привокзалка»: место встречи изменить нельзя",
        shortDescription: "Изучить необычные достопримечательности микрорайона за ж/д вокзалом Омска!",
        images: [image, image2, image3, image4, image3],
        map: map,
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

    it("Проверка рендеринга компонента, если экскурсия не в избранном", () => {

        (useTourTrackingContext as MockedFunction<() => TourTrackingContextType>).mockReturnValue({
            context: {
                favourites: [],
                viewed: []
            },
            addToFav: vi.fn(),
            deleteFromFav: vi.fn(),
            addToViewed: vi.fn()
        })

        const { container } = render(<ToFavourite tour={mockTour}/>)

        expect(screen.getByRole("button")).toBeInTheDocument()
        expect(screen.getByTestId("heart-icon")).toBeInTheDocument()
        expect(container).toMatchSnapshot()

    })

    it("Проверка рендеринга компонента, если экскурсия в избранном", () => {

        (useTourTrackingContext as MockedFunction<() => TourTrackingContextType>).mockReturnValue({
            context: {
                favourites: [mockTour],
                viewed: []
            },
            addToFav: vi.fn(),
            deleteFromFav: vi.fn(),
            addToViewed: vi.fn()
        })

        const { container } = render(<ToFavourite tour={mockTour}/>)

        expect(screen.getByTestId("fav-icon")).toBeInTheDocument()
        expect(container).toMatchSnapshot()

    })

    it("Проверка добавление в избранное", async () => {

        (useTourTrackingContext as MockedFunction<() => TourTrackingContextType>).mockReturnValue({
            context: {
                favourites: [],
                viewed: []
            },
            addToFav: addToFavMock,
            deleteFromFav: vi.fn(),
            addToViewed: vi.fn()
        })

        const { container } = render(<ToFavourite tour={mockTour}/>)

        await userEvent.click(screen.getByRole("button"))

        expect(addToFavMock).toHaveBeenCalledWith(mockTour)
        expect(container).toMatchSnapshot()

    })

    it("Проверка удаления из избранного", async () => {

        (useTourTrackingContext as MockedFunction<() => TourTrackingContextType>).mockReturnValue({
            context: {
                favourites: [mockTour],
                viewed: []
            },
            addToFav: vi.fn(),
            deleteFromFav: deleteFromFavMock,
            addToViewed: vi.fn()
        })

        const { container } = render(<ToFavourite tour={mockTour}/>)

        await userEvent.click(screen.getByRole("button"))

        expect(deleteFromFavMock).toHaveBeenCalledWith(mockTour)
        expect(container).toMatchSnapshot()

    })

})