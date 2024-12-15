import {FC} from "react";

type MarkerProps = {
    value: string
}

export const Marker: FC<MarkerProps> = ({value}) => {
    return (
        <div className={"flex items-center justify-center w-8 h-6 bg-secondary-green rounded-xl text-grayscale-0 text-sm"}>
            {value}
        </div>
    );
};