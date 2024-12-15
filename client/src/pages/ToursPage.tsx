import {FC} from "react";
import {useOrientation} from "@/shared/hooks";
import {Orientation} from "@/shared/types";
import {Form, Tours} from "@/widgets";
import container from "@/app/styles/containers.module.css"
import pages from "@/app/styles/pages.module.css"
import {SidebarButton} from "@/shared/ui";
import favourite from "@/shared/assets/icons/favourite-secondary.svg";

export const ToursPage: FC = () => {

    const orientation = useOrientation(Orientation.VERTICAL)

    return (
        <div className={pages.tours}>
            <div className={container.sidebar}>
                <Form orientation={orientation}/>
                <SidebarButton
                    image={favourite}
                    label={"Избранное"}
                />
            </div>
            <Tours/>
        </div>
    );
};