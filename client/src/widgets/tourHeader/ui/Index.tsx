import {FC} from "react";
import {ITour} from "@/shared/types";
import {Button, FavouriteButton, GroupPrice, Rating, TourParams} from "@/shared/ui";
import style from "./style.module.css"

type HeaderProps = {
    tour: ITour
}

export const Index: FC<HeaderProps> = ({tour}) => {
    return (
        <div className={style.container}>
            <div className={style.startCol}>
                <h2 className={style.title}>{tour.title}</h2>
                <TourParams duration={tour.duration} length={tour.routeLength}/>
                <Rating rating={tour.rating} ratingCount={tour.ratingCount}/>
            </div>
            <div className={style.endCol}>
                <GroupPrice price={tour.price}/>
                <div className={style.buttons}>
                    <FavouriteButton/>
                    <Button>Выбрать</Button>
                </div>
            </div>
        </div>
    );
};