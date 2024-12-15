import {FC, useState} from "react";
import {Heart} from "lucide-react";
import favourite from "@/shared/assets/icons/favourite.svg"

export const FavouriteButton: FC = () => {

    const [isActive, setIsActive] = useState<boolean>(false)

    const clickHandler = () => {
        setIsActive(!isActive)
    }

    return (
        <button onClick={clickHandler}>
            {
                isActive
                    ?
                    <img alt={"favourite"} src={favourite}/>
                    :
                    <Heart className={"w-6 h-6 text-grayscale-500 hover:text-secondary-red transition-all ease-in-out duration-300"}/>
            }
        </button>
    );
};