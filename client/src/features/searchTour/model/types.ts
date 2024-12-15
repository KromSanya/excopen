import {SearchParamsType, TourAccessibility} from "@/shared/types";

export type SearchTourContextType = {
    searchParams: SearchParamsType
    setLocation: (location: string) => void
    setAccessibility: (type: TourAccessibility) => void
    setByCity: (byCity: boolean) => void
    setDate: (date: Date | undefined) => void
}