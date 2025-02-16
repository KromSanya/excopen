import {FC} from "react";
import style from "./style.module.css"

export const Index: FC = () => {
    return (
        <div className={style.container}>
            <h2 className={style.number}>
                8 800 533-78-67
            </h2>
            <p>
                Для звонков из-за рубежа: +7 499 285-97-67
            </p>
        </div>
    );
};