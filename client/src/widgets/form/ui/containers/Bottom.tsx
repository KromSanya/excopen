import {FC, ReactNode} from "react";
import {Orientation} from "@/shared/types";

type Bottom = {
    children: ReactNode
    orientation: Orientation
}

export const Bottom: FC<Bottom> = ({children, orientation}) => {
    return (
        <div className={
            orientation === Orientation.HORIZONTAL ?
                "flex justify-end" :
                "w-full flex items-start px-4"
        }>
            {children}
        </div>
    );
};