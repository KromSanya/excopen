import {FC} from "react";
import watch from "@/shared/assets/icons/watch.svg";

type HoursProps = {
    duration: string
}

export const Hours: FC<HoursProps> = ({duration}) => {
    return (
        <div className={"flex flex-row gap-2 items-center text-grayscale-400 text-base"}>
            <img width={20} height={20} alt={"watch"} src={watch}/>
            <p>{duration}</p>
        </div>
    );
};