import {FC} from "react";
import {Link} from "react-router-dom";
import style from "./style.module.css"
import contributor from "@/shared/assets/icons/contributor.svg";
import next from "@/shared/assets/icons/next-secondary.svg";

type ContributorButtonProps = {
    name: string | undefined
    link: string
}

export const Index: FC<ContributorButtonProps> = ({name, link}) => {
    return (
        <Link className={style.container} to={link}>

            <img width={32} height={32} alt={"contributor"} src={contributor}/>

            <div className={style.desc}>
                <span className={style.name}>
                    {name}
                </span>
                <span>
                    Представитель команды гидов
                </span>
            </div>

            <img width={24} height={24} alt={"next"} src={next}/>

        </Link>
    );
};