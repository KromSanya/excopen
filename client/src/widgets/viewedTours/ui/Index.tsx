import {FC, useState} from "react";
import style from "./style.module.css";
import {TourCard} from "@/widgets";
import {Header} from "./header";
import {searchTourByCity, useTourViewContext} from "@/features";

export const Index: FC = () => {

    const {viewedTours} = useTourViewContext()

    const [location, setLocation] = useState<string>("")
    const tours = searchTourByCity(viewedTours, location)

    return (
        <div className={style.container}>
            <Header onChangeHandler={setLocation}/>
            {tours.map(tour => (
                <TourCard key={tour.id} tour={tour}/>
            ))}
        </div>
    );
};