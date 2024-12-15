import {FC} from "react";
import style from "./style.module.css"

type IndexProps = {
    cities: string[]
}

export const Index: FC<IndexProps> = ({cities}) => {
    return (
        <div className={style.container}>
            {
                cities.length === 0 && <span className={style.warning}>Город не найден</span>
            }
            {cities.map(city => (
                <span key={city} className={style.city}>
                    {city}
                </span>
            ))}
        </div>
    );
};