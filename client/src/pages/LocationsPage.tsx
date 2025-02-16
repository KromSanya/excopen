import {FC, useEffect} from "react";
import style from "@/app/styles/pages.module.css";
import {Cities, Overview} from "@/widgets";

export const LocationsPage: FC = () => {

    useEffect(() => window.scroll(0,0), []);

    return (
        <div className={style.locations}>
            <Overview/>
            <Cities/>
        </div>
    );
};