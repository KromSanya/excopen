import {ITour} from "@/shared/types";
import {SortByPriceType} from "./types.ts";

export const sortByPrice = (tours: ITour[], type: SortByPriceType): ITour[] => {
    return tours.sort((a,b) => {
        if (type === SortByPriceType.ASCENDING) return a.price - b.price
        else return b.price - a.price
    })
}