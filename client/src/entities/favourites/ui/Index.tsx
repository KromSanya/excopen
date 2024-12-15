import {FC, useState} from "react";
import {Heart} from "lucide-react";
import favourite from "@/shared/assets/icons/favourite.svg"
import style from "./style.module.css"
import {ITour} from "@/shared/types";
import {useDeleteFavourite, useGetFavourites, usePostFavourite} from "@/entities/favourites/api";

type ToFavProps = {
    tour: ITour
}

export const Index: FC<ToFavProps> = ({tour}) => {

    const favourites = useGetFavourites()
    const postMutation = usePostFavourite()
    const deleteMutation = useDeleteFavourite()

    const defaultValue = favourites.some(favTour => favTour.id === tour.id)

    const [isActive, setIsActive] = useState<boolean>(defaultValue)

    const clickHandler = () => {
        if (isActive) postMutation.mutate({id: tour.id})
        if (!isActive && defaultValue) deleteMutation.mutate({id: tour.id})
        setIsActive(!isActive)
    };

    return (
        <button onClick={clickHandler}>
            {
                isActive ?
                    <img alt={"favourite"} src={favourite}/> :
                    <Heart className={style.heart}/>
            }
        </button>
    );
};