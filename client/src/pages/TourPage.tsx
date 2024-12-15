import {FC} from "react";
import {TourHeader} from "@/widgets/tourHeader";
import {Carousel, TourDescription, Sidebar} from "@/widgets";
import pages from "@/app/styles/pages.module.css"
import containers from "@/app/styles/containers.module.css"
import {useParams} from "react-router-dom";
import {ToursArray} from "@/shared/assets/tempData/ToursArray.ts";

export const TourPage: FC = () => {

    const { title } = useParams<{ title: string }>();
    const tour = ToursArray.find(tour => tour.title === decodeURIComponent(title || ""));
    if (!tour) return <div>Экскурсия не найдена</div>;

    return (
        <div className={pages.tour}>
            <TourHeader tour={tour}/>
            <Carousel images={tour.images} map={tour.map}/>
            <div className={containers.tourMain}>
                <TourDescription tour={tour}/>
                <Sidebar tour={tour}/>
            </div>
        </div>
    );
};