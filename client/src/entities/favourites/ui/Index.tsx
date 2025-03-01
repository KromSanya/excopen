import {FC, useEffect, useState} from "react";
import { Heart } from "lucide-react";
import favourite from "@/shared/assets/icons/favourite.svg";
import style from "./style.module.css";
import { ITour } from "@/shared/types";
import { useTourTrackingContext } from "@/features";

type ToFavProps = {
    tour: ITour;
};

export const Index: FC<ToFavProps> = ({ tour }) => {

    const { context, addToFav, deleteFromFav } = useTourTrackingContext()
    const [isActive, setIsActive] = useState<boolean>(false)

    useEffect(() => {
        setIsActive(context.favourites.some(fav => fav.id === tour.id))
    }, [context.favourites, tour.id]);

    const clickHandler = () => {
        if (!isActive) addToFav(tour)
        else deleteFromFav(tour)
        setIsActive(!isActive)
    }

    return (
        <button onClick={clickHandler}>
            {isActive ? (
                <img data-testid={"fav-icon"} alt={"favourite"} src={favourite} />
            ) : (
                <Heart data-testid={"heart-icon"} className={style.heart} />
            )}
        </button>
    );
};