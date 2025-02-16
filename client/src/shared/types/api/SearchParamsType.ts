import {RangeType} from "@/shared/types";

export type SearchParamsType = {
    location: string
    date: RangeType
    accessibility: string
    byCity: boolean
}