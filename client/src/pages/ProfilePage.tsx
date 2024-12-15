import {FC} from "react";
import pages from "@/app/styles/pages.module.css";
import container from "@/app/styles/containers.module.css";
import {ViewedTours} from "@/widgets";
import {EditProfile, SidebarButton} from "@/shared/ui";
import users from "@/shared/assets/icons/users.svg";
import favourite from "@/shared/assets/icons/favourite-secondary.svg";

export const ProfilePage: FC = () => {

    return (
        <div className={pages.profile}>
            <div className={container.profileSidebar}>
                <EditProfile/>
                <SidebarButton
                    image={favourite}
                    label={"Избранное"}
                />
                <SidebarButton
                    label={"Предложенные"}
                    image={users}
                />
            </div>
            <ViewedTours/>
        </div>
    );
};