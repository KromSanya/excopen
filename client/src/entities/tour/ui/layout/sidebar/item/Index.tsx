import {FC} from "react";
import style from "./style.module.css"

type ItemProps = {
    option: string
    value: string | undefined
}

export const Index: FC<ItemProps> = ({option, value}) => {
    return (
        <div className={style.container}>
            <p className={style.option}>
                {option}
            </p>
            <p className={style.value}>
                {value}
            </p>
        </div>
    );
};