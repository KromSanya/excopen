import {FC} from "react";
import style from "./style.module.css"

export const Index: FC = () => {
    return (
        <div className={style.container}>
            <p className={style.text}>
                Сервис предоставляет ООО “Т-Путешествия”, ОГРН 1227700720158
                <br/>
                В рамках студенческой производственной практики ОмГУ Х Т-Банк
                <br/>
                @ 2024, АО “ТБанк”, неофициальный сайт
            </p>
        </div>
    );
};