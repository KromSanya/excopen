import {FC} from "react";
import style from "./style.module.css"
import {cn} from "@/app/lib/utils.ts";

type ReviewCardProps = {
    name: string
    rating: number
    positiveText: string
    negativeText: string
    withChildren: boolean
    personCount: number
}

export const Index: FC<ReviewCardProps> = ({rating, positiveText, negativeText, withChildren, personCount, name}) => {
    return (
        <div className={style.container}>
            <div className={style.header}>
                <div className={style.person}>
                    <span className={style.name}>{name}</span>
                    <span className={style.text}>
                        {withChildren ? "Путешествовал(а) с детьми" : "Путешествовал(а) без детей"},
                        {personCount} персоны
                    </span>
                </div>
                <div className={cn(
                    "flex items-center justify-center w-8 h-6 rounded-xl text-grayscale-0 text-sm",
                    (rating <= 3) && "bg-red-gr",
                    (rating > 3) && "bg-green-gr",
                )}>
                    {rating}
                </div>
            </div>
            <div className={style.reviews}>
                <div className={style.review}>
                    <div className={style.positive}></div>
                    <span className={style.text}>{positiveText}</span>
                </div>
                <div className={style.review}>
                    <div className={style.negative}></div>
                    <span className={style.text}>{negativeText}</span>
                </div>
            </div>
        </div>
    );
};