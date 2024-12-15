import {ITour} from "@/shared/types";

export type TourViewContextType = {
    viewedTours: ITour[]
    setViewedTours: (viewedTour: ITour[]) => void
}