import {FC} from "react";
import style from "./style.module.css"

type PriceProps = {
    price: number
}

export const Index: FC<PriceProps> = ({price}) => {
    return (
        <div className={style.container}>
            <span className={style.price}>
                {price} ₽
            </span>
            <span className={style.desc}>
                за человека
            </span>
        </div>
    );
};