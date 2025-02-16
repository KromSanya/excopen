import {IReview} from "@/shared/types";
import {FC} from "react";
import {Button} from "@/shared/ui";

type PaginationProps = {
    visibleReviews: number
    setVisibleReviews:  React.Dispatch<React.SetStateAction<number>>
    reviews: IReview[]
}

export const Index: FC<PaginationProps> = ({visibleReviews, setVisibleReviews, reviews}) => {

    const loadTours = () => setVisibleReviews(prev => prev + 2)
    const unloadTours = () => setVisibleReviews(prev => prev - 2)

    return (
        <div className={"flex flex-row gap-4"}>
            {
                visibleReviews < reviews.length &&
                <Button onClick={loadTours} variant={"secondary"}>
                    Загрузить еще
                </Button>
            }
            {
                visibleReviews > 2 &&
                <Button onClick={unloadTours} variant={"secondary"}>
                    Скрыть
                </Button>
            }
        </div>
    );
};