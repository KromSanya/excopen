import {FC, ReactNode} from "react";
import {Orientation} from "@/shared/types";
import {cn} from "@/app/lib/utils.ts";

type SubContainerTopProps = {
    children: ReactNode
    orientation: Orientation
}

export const Header: FC<SubContainerTopProps> = ({children, orientation}) => {
    return (
        <div className={cn("flex gap-4",
            orientation === Orientation.HORIZONTAL ?
                "flex-row justify-between" :
                "flex-col items-center gap-4"
        )}>
            {children}
        </div>
    );
};