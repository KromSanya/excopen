import {ITour} from "@/shared/types";

export interface IOrder {
    id: number
    tour: ITour
    groupCapacity: number
}