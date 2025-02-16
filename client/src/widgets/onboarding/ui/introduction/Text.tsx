import {FC} from "react";
import style from "./text.module.css";

export const Text: FC = () => {
    return (
        <>
            <p className={style.heading}>
                Теперь в Т-путешествия мы поможем Вам в поиске самой интересной экскурсии!
            </p>
            <p className={style.heading}>
                Получите самый большой набор экскурсий по месту Вашего отдыха.
                Выберите варианты, которые подойдут именно Вам
            </p>
            <p className={style.desc}>
                Проект выполнен группой студентов в формате учебной практики.
                Будем благодарны любой обратной связи.
            </p>
        </>
    );
};