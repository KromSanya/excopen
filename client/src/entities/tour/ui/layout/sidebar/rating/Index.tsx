import {FC} from "react";
import style from "./style.module.css"
import {Marker} from "@/shared/ui/rating/Marker.tsx";

type RatingProps = {
    option: string
    rating: number
    ratingCount: number
}

export const Index: FC<RatingProps> = ({option, rating, ratingCount}) => {
    return (
        <div className={style.container}>

            <p className={style.option}>
                {option}
            </p>

            <div className={style.rating}>
                <Marker value={rating}/>
                <p className={style.count}>
                    {ratingCount} оценок
                </p>
            </div>

        </div>
    );
};