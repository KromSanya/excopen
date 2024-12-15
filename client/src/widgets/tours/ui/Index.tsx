import {FC, useState} from "react";
import {Header} from "./header";
import {TourCard} from "@/widgets";
import {ToursArray} from "@/shared/assets/tempData/ToursArray.ts";
import style from "./style.module.css"
import {Pagination} from "./pagination";
import {useParams} from "react-router-dom";

export const Index: FC = () => {

    const {location} = useParams<{location : string}>()

    const [
        visibleTours,
        setVisibleTours
    ] = useState<number>(3)

    return (
        <div className={style.container}>

            <Header city={location} count={888}/>

            {ToursArray.slice(0,visibleTours).map(tour => (
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