import {FC} from "react";
import {Hours} from "@/shared/ui/tourParams/Hours.tsx";
import {Distance} from "@/shared/ui/tourParams/Distance.tsx";
import {ParamsContainer} from "@/shared/ui/tourParams/ParamsContainer.tsx";

type TourParamsProps = {
    duration: string
    length: number
}

export const TourParams:FC<TourParamsProps> = ({duration, length}) => {
    return (
        <ParamsContainer>
            <Hours duration={duration}/>
            <Distance length={length}/>
        </ParamsContainer>
    );
};