import {FC} from "react";
import navigate from "@/shared/assets/icons/navigate.svg";

type DistanceProps = {
    length: number
}

export const Distance: FC<DistanceProps> = ({length}) => {
    return (
        <div className={"flex flex-row gap-2 items-center text-grayscale-400 text-base"}>
            <img width={20} height={20} alt={"navigate"} src={navigate}/>
            <p>{length} км пешком</p>
        </div>
    );
};