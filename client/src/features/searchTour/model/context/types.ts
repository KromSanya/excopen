import {RangeType, SearchParamsType} from "@/shared/types";

export type SearchContextType = {
    context: {
        searchParams: SearchParamsType
        isSearch: boolean
    }
    setLocation: (location: string) => void
    setAccessibility: (accessibility: string) => void
    setByCity: (byCity: boolean) => void
    setDate: (date: RangeType) => void
    setIsSearch: (isSearch: boolean) => void
}