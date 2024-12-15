import {Link} from "react-router-dom";
import {FC} from "react";
import {links} from "@/widgets/footer/utils";
import style from "./style.module.css"

export const Index: FC = () => {
    return (
        <div className={style.container}>
            {links.map((link, index) => (
                <Link key={index} to={link.link}>
                    {link.label}
                </Link>
            ))}
        </div>
    );
};