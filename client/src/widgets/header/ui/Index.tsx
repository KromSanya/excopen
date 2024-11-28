import {FC} from 'react';
import {ProfileButton} from "@/widgets/header/ui/ProfileButton.tsx";
import logo from "@/shared/assets/icons/logo.svg"
import {Link} from "react-router-dom";
import {RouteNames} from "@/shared/types";
import style from "./style.module.css"

export const Header: FC = () => {

    return (
        <header className={style.container}>
            <Link to={`/${RouteNames.MAIN}`}>
                <img alt={"logo"} width={100} height={30} src={logo}/>
            </Link>
            <ProfileButton/>
        </header>
    );
};