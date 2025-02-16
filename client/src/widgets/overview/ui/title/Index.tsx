import {FC} from "react";
import style from "./style.module.css"

export const Title: FC = () => {
    return (
        <section className={style.container}>
            <h1 className={style.heading}>
                Найдем Вашу идеальную экскурсию
            </h1>
            <p className={style.description}>
                Интересное со всего мира с Т-Банком
            </p>
        </section>
    );
};