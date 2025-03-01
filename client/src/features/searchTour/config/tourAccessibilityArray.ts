import {TourAccessibility} from "@/shared/types";

type TourAccessibilityArrayType = {
    value: string
    label: string
}

export const tourAccessibilityArray: TourAccessibilityArrayType[] = [
    {value: TourAccessibility.WITH_CHILDREN, label: "Можно с детьми"},
    {value: TourAccessibility.WITHOUT_CHILDREN, label: "Без детей"}
]