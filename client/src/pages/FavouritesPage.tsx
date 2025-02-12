import {FC} from "react";
import pages from "@/app/styles/pages.module.css";
import {useTourTrackingContext} from "@/features";
import {TourCard} from "@/entities";

export const FavouritesPage: FC = () => {

    const {context} = useTourTrackingContext()

    return (
        <div className={pages.favorites}>
            <h1 className={"text-grayscale-500 text-4xl font-medium"}>Избранное</h1>
            {
                context.favourites.length === 0 &&
                <span className={"text-sm text-grayscale-400"}>Список избранных экскурсий пуст :(</span>
            }
            <div className={"min-h-screen w-4/5 flex flex-col gap-8"}>
                {context.favourites.map(tour => <TourCard key={tour.id} tour={tour}/>)}
            </div>
        </div>
    );
};