import {locationsArray} from "@/shared/assets/tempData/locationsArray.ts";

export const validateByCity = (value: string): boolean => {
    return locationsArray.some(
        location => location.city.toLowerCase() === value.toLowerCase()
    )
}