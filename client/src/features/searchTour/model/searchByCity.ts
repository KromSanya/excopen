import {ITour} from "@/shared/types";

export const searchByCity = (tours: ITour[], location: string): ITour[] => {
    return tours.filter(tour => tour.location?.city.toUpperCase().startsWith(location.toUpperCase()))
}