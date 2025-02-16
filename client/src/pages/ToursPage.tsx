import {FC} from "react";
import {Orientation} from "@/shared/types";
import container from "@/app/styles/containers.module.css"
import pages from "@/app/styles/pages.module.css"
import {SidebarButton} from "@/shared/ui";
import favourite from "@/shared/assets/icons/favourite-secondary.svg";
import {Form} from "@/features";
import {Tours} from "@/entities";

export const ToursPage: FC = () => {
    return (
        <div className={pages.tours}>
            <div className={container.sidebar}>
                <Form orientation={Orientation.VERTICAL}/>
                <SidebarButton image={favourite} label={"Избранное"}/>
            </div>
            <Tours/>
        </div>
    );
};