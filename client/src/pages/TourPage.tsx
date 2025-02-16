import {FC} from "react";
import {useParams} from "react-router-dom";
import {ToursArray} from "@/shared/assets/tempData/ToursArray.ts";
import {TourLayout} from "@/entities";

export const TourPage: FC = () => {

    const { title } = useParams<{ title: string }>();
    const tour = ToursArray.find(tour => tour.title === decodeURIComponent(
        title || ""
    ));

    if (!tour) return <div>Экскурсия не найдена</div>;
    return <TourLayout tour={tour}/>

};