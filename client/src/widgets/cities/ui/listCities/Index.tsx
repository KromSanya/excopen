import {FC} from "react";
import style from "./style.module.css"
import {useSearchContext} from "@/features";

type IndexProps = {
    cities: string[]
}

export const Index: FC<IndexProps> = ({cities}) => {

    const {setLocation} = useSearchContext()

    const clickHandler = (newCity: string) => {
        window.scroll(0,0)
        setLocation(newCity)
    }

    return (
        <div className={style.container}>
            {cities.length === 0 && <span className={style.warning}>Город не найден</span>}
            {cities.map(city => (
                <span key={city} className={style.city} onClick={() => clickHandler(city)}>
                    {city}
                </span>
            ))}
        </div>
    );
};