import {FC} from "react";
import {LocationsList, SearchTourBar} from "@/widgets";
import {useOrientation} from "@/shared/hooks";
import {Orientation} from "@/shared/types";
import style from "@/app/styles/pages.module.css"
import {Title} from "@/shared/ui/title";

export const HomePage: FC = () => {

    const orientation = useOrientation(Orientation.HORIZONTAL)

    return (
        <div className={style.home}>
            <Title/>
            <SearchTourBar orientation={orientation}/>
            <LocationsList/>
        </div>
    );
};