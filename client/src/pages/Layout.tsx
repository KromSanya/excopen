import {Outlet, useNavigate} from "react-router-dom"
import {FC, useEffect} from "react";
import {Footer, Header} from "@/widgets";
import style from "@/app/styles/pages.module.css"
import {RouteNames} from "@/shared/types";
import {Breadcrumbs} from "@/features";

export const Layout: FC = () => {

    const navigate = useNavigate()

    useEffect(() => {
        if (window.location.pathname === "/") navigate(RouteNames.MAIN)
    }, [navigate]);

    return (
        <div className={style.layout}>
            <Header/>
            <main className={style.main}>
                <Breadcrumbs/>
                <Outlet/>
            </main>
            <Footer/>
        </div>
    );
};