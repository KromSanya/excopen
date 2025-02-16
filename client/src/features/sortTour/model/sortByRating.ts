import {ITour} from "@/shared/types";

export const sortByRating = (tours: ITour[]): ITour[] => tours.sort(
    (a,b) => b.rating - a.rating
)