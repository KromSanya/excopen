import {FC} from "react";
import style from "./style.module.css";
import {Select} from "@/features";
import {Drawer} from "./drawer/index.ts";

type HeaderProps = {
    city: string | undefined
    count: number
    sortType: string
    setSortType: (sortType: string) => void
}

export const Index: FC<HeaderProps> = (
    {city, count, sortType, setSortType}
) => {

    return (
        <header className={style.container}>
            <div className={style.content}>
                <h1 className={style.title}>{city}</h1>
                <span className={style.desc}>{count} найденных экскурсий</span>
            </div>
            <div className={style.options}>
                <Drawer/>
                <Select sortValue={sortType} setSortValue={setSortType}/>
            </div>
        </header>
    );
};