import style from "@/widgets/tours/ui/style.module.css";
import {Button} from "@/shared/ui";
import {FC} from "react";
import {ITour} from "@/shared/types";

type PaginationProps = {
    visibleTours: number
    setVisibleTours: (value: number) => void
    tours: ITour[]
}

export const Index: FC<PaginationProps> = ({tours, visibleTours, setVisibleTours}) => {

    const loadTours = () => {
        setVisibleTours(visibleTours => visibleTours + 3)
    }

    return (
        visibleTours < tours.length &&
        <div className={style.pagination}>
            <Button onClick={loadTours} variant={"outline"}>
                Загрузить еще
            </Button>
        </div>
    );
};