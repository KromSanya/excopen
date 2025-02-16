import {IUser} from "@/shared/types/entities/IUser.ts";
import {OrderObject} from "@/shared/assets/tempData/OrderObject.ts";

export const UserObject: IUser = {
    id: 0,
    name: "Ivan",
    surname: "Ivanov",
    email: "ivanov@mail.ru",
    orders: [OrderObject, OrderObject]
}