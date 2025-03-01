import {IOrder} from "@/shared/types/entities/IOrder.ts";

export interface IUser {
    id: number
    name: string
    surname: string
    email: string
    orders: IOrder[]
}