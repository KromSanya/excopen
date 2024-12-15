import {LocationsArrayForFeature} from "@/shared/assets/tempData/LocationsArrayForFeature.ts";

export const searchLocation = (location: string): string[] => {
    return location.length === 0
        ? []
        : LocationsArrayForFeature
            .filter(i => i.city.toUpperCase().startsWith(location.toUpperCase()))
            .map(i => i.city)
}