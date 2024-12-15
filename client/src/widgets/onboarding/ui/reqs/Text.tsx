import {FC} from "react";
import style from "./styles/text.module.css"

export const Text: FC = () => {
    return (
        <>
            <p className={style.largeHeading}>
                Найдем Вашу идеальную экскурсию
            </p>
            <div className={style.descContainer}>
                <p className={style.smallHeading}>
                    Для начала расскажите про Ваши предпочтения на отдыхе!
                </p>
                <p className={style.desc}>
                    Выберите из набора наиболее интересные теги, это поможет сделать рекомендации точнее!
                </p>
            </div>
        </>
    );
};