import {FC} from "react";
import next from "@/shared/assets/icons/next-secondary.svg";
import contributor from "@/shared/assets/icons/contributor.svg";
import style from "./style.module.css"
import {useNavigate} from "react-router-dom";
import {RouteNames} from "@/shared/types";
import {Marker} from "@/shared/ui/rating/Marker.tsx";

type ContributorProps = {
    name: string
    description: string
    rating: number
}

export const Index: FC<ContributorProps> = ({name, description, rating}) => {

    const navigate = useNavigate()

    const clickHandler = () => navigate(`/${RouteNames.CONTRIBUTOR}/${encodeURIComponent(name)}`)

    return (
        <div className={style.container}>
            <div className={style.info}>
                <div onClick={clickHandler} className={style.name}>
                    <span>{name} – представитель команды гидов</span>
                    <img width={20} height={20} alt={"next"} src={next}/>
                </div>
                <p className={style.desc}>{description}</p>
            </div>
            <div className={style.avatar}>
                <div className={style.icon}>
                    <img width={48} height={48} alt={"contributor"} src={contributor}/>
                    <div className={style.marker}>
                        <Marker value={rating}/>
                    </div>
                </div>
            </div>
        </div>
    );
};