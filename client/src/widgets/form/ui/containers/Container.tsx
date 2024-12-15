import {FC, ReactNode} from "react";
import {Orientation} from "@/shared/types";
import {cn} from "@/app/lib/utils.ts";

type ContainerProps = {
    children: ReactNode
    orientation: Orientation
}

export const Container: FC<ContainerProps> = ({children, orientation}) => {
    return (
        <div
            className={cn("flex flex-col gap-4 relative rounded-2xl bg-grayscale-0 ",
                orientation === Orientation.HORIZONTAL ?
                    "lg:h-32 py-4 px-6" :
                    "h-[420px] w-[340px] py-8 px-4"
            )}
        >
            {children}
        </div>
    );
};