import {RatingContainer} from "@/shared/ui/rating/RatingContainer.tsx";
import {Marker} from "@/shared/ui/rating/Marker.tsx";
import {RatingDescription} from "@/shared/ui/rating/RatingDescription.tsx";
import {FC} from "react";

type RatingProps = {
    rating: number
    ratingCount: number
}

export const Rating: FC<RatingProps> = ({rating, ratingCount}) => {
    return (
        <RatingContainer>
            <Marker value={rating}/>
            <RatingDescription value={ratingCount}/>
        </RatingContainer>
    );
};