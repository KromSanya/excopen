import {FC} from "react";

type PriceProps = {
    value: number
}

export const Price: FC<PriceProps> = ({value}) => {
    return (
        <p className={"text-2xl text-grayscale-500 font-medium"}>
            {value} ₽
        </p>
    );
};