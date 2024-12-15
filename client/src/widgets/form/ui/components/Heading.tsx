import {FC} from "react";
import {Orientation} from "@/shared/types";

type HeadingProps = {
    value: string
    orientation: Orientation
}

export const Heading: FC<HeadingProps> = ({value, orientation}) => {
    return (
        <div className={
            orientation === Orientation.VERTICAL
                ?
                "w-full px-4 font-medium text-xl text-grayscale-500"
                :
                "hidden"
        }>
            <h3>{value}</h3>
        </div>
    );
};