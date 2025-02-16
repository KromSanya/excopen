import {FC} from "react";

type RatingDescriptionProps = {
    value: number
}

export const RatingDescription: FC<RatingDescriptionProps> = ({value}) => {
    return (
        <p className={"text-grayscale-400 text-base"}>
            На основе {value} оценок
        </p>
    );
};