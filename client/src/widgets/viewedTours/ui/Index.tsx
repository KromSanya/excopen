import {FC, useState} from "react";
import style from "./style.module.css";
import {Header} from "./header";
import {searchByCity, useTourTrackingContext} from "@/features";
import {TourCard} from "@/entities";

export const Index: FC = () => {

    const {context} = useTourTrackingContext()
    const [location, setLocation] = useState<string>("")
    const tours = searchByCity(context.viewed, location)

    return (
        <div className={style.container}>
            <Header onChangeHandler={setLocation}/>
            {tours.length === 0
                ?
                <div className={"w-full text-center text-xl"}>
                    <span>Экскурсии не найдены :(</span>
                </div>
                :
                tours.map(tour => <TourCard key={tour.id} tour={tour}/>)
            }
        </div>
    );
};