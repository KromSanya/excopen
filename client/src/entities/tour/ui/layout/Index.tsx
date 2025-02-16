import {FC} from "react";
import {ITour} from "@/shared/types";
import style from "./style.module.css";
import {Header} from "./header";
import {Carousel} from "./carousel";
import {Sidebar} from "./sidebar";
import {Description} from "./description";

type LayoutProps = {
    tour: ITour;
}

export const Index: FC<LayoutProps> = ({tour}) => {
    return (
        <div className={style.container}>
            <Header tour={tour}/>
            <Carousel images={tour.images} map={tour.map}/>
            <div className={style.subContainer}>
                <Description tour={tour}/>
                <Sidebar tour={tour}/>
            </div>
        </div>
    );
};