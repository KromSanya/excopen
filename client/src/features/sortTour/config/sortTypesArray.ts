import {SortValues} from "@/shared/types/features";

type SortTypesArrayType = {
    value: string
    label: string
}

export const sortTypesArray: SortTypesArrayType[] = [
    {value: SortValues.FOR_POPULAR, label: "По популярности"},
    {value: SortValues.FOR_CHEAP, label: "Сначала дешевые"},
    {value: SortValues.FOR_EXPENSIVE, label: "Сначала дорогие"},
    {value: SortValues.FOR_RATING, label: "По рейтингу"}
]