import {FC} from "react";
import {cn} from "@/app/lib/utils.ts";

type MarkerProps = {
    value: number
}

export const Marker: FC<MarkerProps> = ({value}) => {
    return (
        <div className={cn(
            "flex items-center justify-center w-8 h-6 rounded-xl text-grayscale-0 text-sm",
            (value <= 2) && "bg-red-500",
            (value > 2 && value <= 4) && "bg-orange-500",
            (value > 4) && "bg-secondary-green",
        )}>
            {value}
        </div>
    );
};