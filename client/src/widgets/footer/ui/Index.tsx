import {FC} from "react";
import style from "./style.module.css"
import {Info} from "./info";
import {Nav} from "./nav";
import {Annotation} from "./annotation";
import {SupportButton} from "@/shared/ui";

export const Index: FC = () => {
    return (
        <footer className={style.container}>
            <div className={style.info}>
                <Nav/>
                <Info/>
            </div>
            <hr className={style.separator}/>
            <Annotation/>
            <SupportButton/>
        </footer>
    );
};