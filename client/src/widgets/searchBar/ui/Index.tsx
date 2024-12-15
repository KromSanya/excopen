import {FC, useState} from "react";
import style from "./style.module.css"
import {Input} from "@/shared/ui";
import {ListLetter} from "./listLetter";
import {ListCities} from "./listCities";
import {searchLocation} from "@/features";

export const Index: FC = () => {

    const [location, setLocation] = useState<string>("")
    const [isActive, setIsActive] = useState<boolean>(false)

    const cities = searchLocation(location)

    const focusHandler = () => setIsActive(true)
    const blurHandler = () => setIsActive(false)

    return (
        <div className={style.container}>
            <h2 className={style.heading}>
                Список городов
            </h2>
            <Input
                onFocus={focusHandler}
                onBlur={blurHandler}
                onChangeHandler={setLocation}
                placeholder={"Искать"}
            />
            <ListLetter
                isActive={isActive}
                onChange={setLocation}
            />
            <ListCities
                cities={cities}
            />
        </div>
    );
};