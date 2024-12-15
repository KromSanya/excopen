import {FC} from "react";
import style from "@/app/styles/pages.module.css";
import {Title} from "@/shared/ui/title";
import {Form, Locations, SearchBar} from "@/widgets";
import {useOrientation} from "@/shared/hooks";
import {Orientation} from "@/shared/types";

export const LocationsPage: FC = () => {

    const orientation = useOrientation(Orientation.HORIZONTAL)

    return (
        <div className={style.home}>
            <Title/>
            <Form orientation={orientation}/>
            <Locations/>
            <SearchBar/>
        </div>
    );
};