import {FC, useState} from "react";
import {Header} from "./header";
import {ToursArray} from "@/shared/assets/tempData/ToursArray.ts";
import style from "./style.module.css"
import {useParams} from "react-router-dom";
import {TourCard} from "@/entities";
import {SortValues} from "@/shared/types/features";
import {ITour} from "@/shared/types";
import {sortByPrice, SortByPriceType, sortByRating} from "@/features";
import {Pagination} from "./pagination";

export const Index: FC = () => {

    const {location} = useParams<{location : string}>()
    const [visibleTours, setVisibleTours] = useState<number>(
        3
    )
    const [sortType, setSortType] = useState<string>(
        SortValues.FOR_CHEAP
    )

    let sortedTours: ITour[] = []

    if (sortType === SortValues.FOR_CHEAP) sortedTours = sortByPrice(ToursArray, SortByPriceType.ASCENDING)
    else if (sortType === SortValues.FOR_EXPENSIVE) sortedTours = sortByPrice(ToursArray, SortByPriceType.DESCENDING)
    else if (sortType === SortValues.FOR_RATING) sortedTours = sortByRating(ToursArray)

    return (
        <div className={style.container}>
            <Header city={location} count={888} sortType={sortType} setSortType={setSortType}/>
            {sortedTours.slice(0,visibleTours).map(tour => (
                <TourCard key={tour.id} tour={tour}/>
            ))}
            <Pagination
                visibleTours={visibleTours}
                setVisibleTours={setVisibleTours}
                tours={ToursArray}
            />
        </div>
    );
};