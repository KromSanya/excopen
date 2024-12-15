import {FC} from "react";
import style from "./style.module.css"
import marker from "./marker.module.css";
import {cn} from "@/app/lib/utils.ts";

type RatingProps = {
    option: string
    rating: string
    ratingCount: number
}

export const Index: FC<RatingProps> = ({option, rating, ratingCount}) => {
    return (
        <div className={style.container}>

            <p className={style.option}>
                {option}
            </p>

            <div className={style.rating}>
                <div className={cn(marker.layout, marker.bg, marker.text)}>
                    {rating}
                </div>
                <p className={style.count}>
                    {ratingCount} оценок
                </p>
            </div>

        </div>
    );
};