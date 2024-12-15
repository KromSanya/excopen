import {Outlet} from "react-router-dom"
import {FC} from "react";
import {Breadcrumbs, Footer, Header} from "@/widgets";
import style from "@/app/styles/pages.module.css"

export const Layout: FC = () => {
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