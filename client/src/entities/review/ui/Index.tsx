import {IReview} from "@/shared/types";
import {FC, useState} from "react";
import {Card} from "./card";
import {Header} from "./header";
import {Pagination} from "./pagination";

type ReviewListProps = {
    rating: number
    ratingCount: number
    reviews: IReview[]
}

export const Index: FC<ReviewListProps> = ({reviews, rating, ratingCount}) => {

    const [visibleReviews, setVisibleReviews] = useState<number>(
        2
    )

    return (
        <div className={"flex flex-col gap-8 my-4"}>
            <Header
                rating={rating}
                ratingCount={ratingCount}
            />
            <div className={"flex flex-col gap-4"}>
                {reviews.slice(0, visibleReviews).map(review =>
                    <Card
                        name={review.name}
                        rating={review.rating}
                        positiveText={review.positiveText}
                        negativeText={review.negativeText}
                        withChildren={review.withChildren}
                        personCount={review.personCount}
                    />
                )}
                <Pagination
                    visibleReviews={visibleReviews}
                    setVisibleReviews={setVisibleReviews}
                    reviews={reviews}
                />
            </div>
        </div>
    );
};