import {FC} from "react";
import pages from "@/app/styles/pages.module.css";
import container from "@/app/styles/containers.module.css";
import {ViewedTours} from "@/widgets";
import {EditProfile, SidebarButton} from "@/shared/ui";
import favourite from "@/shared/assets/icons/favourite-secondary.svg";
import {useAuthContext} from "@/app/context";
import {RouteNames, UserRole} from "@/shared/types";
import users from "@/shared/assets/icons/users.svg";
import {Link} from "react-router-dom";

export const ProfilePage: FC = () => {

    const {role} = useAuthContext()

    return (
        <div className={pages.profile}>
            <div className={container.profileSidebar}>
                <EditProfile/>
                <Link to={`/${RouteNames.FAVOURITES}`}>
                    <SidebarButton image={favourite} label={"Избранное"}/>
                </Link>
                {role === UserRole.contributor && <SidebarButton label={"Предложенные"} image={users}/>}
            </div>
            <ViewedTours/>
        </div>
    );
};