import {ITour} from "@/shared/types";

export type TourTrackingContextType = {
    context: {
        viewed: ITour[]
        favourites: ITour[]
    }
    addToFav: (tour: ITour) => void
    deleteFromFav: (tour: ITour) => void
    addToViewed: (tour: ITour) => void
}