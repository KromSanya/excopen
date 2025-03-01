import style from "@/entities/tour/ui/list/style.module.css";
import {Button} from "@/shared/ui";
import {FC} from "react";
import {ITour} from "@/shared/types";

type PaginationProps = {
    visibleTours: number
    setVisibleTours:  React.Dispatch<React.SetStateAction<number>>
    tours: ITour[]
}

export const Index: FC<PaginationProps> = ({tours, visibleTours, setVisibleTours}) => {

    const loadTours = () => {
        setVisibleTours(prev => prev + 3)
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