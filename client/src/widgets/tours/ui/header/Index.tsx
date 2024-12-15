import {FC, useState} from "react";
import {SelectParams, SelectType} from "@/shared/ui";
import {SortValues} from "@/shared/types/features";
import {sortTypesArray} from "@/widgets/tours/utils";
import style from "./style.module.css"

type HeaderProps = {
    city: string | undefined
    count: number
}

export const Index: FC<HeaderProps> = ({city, count}) => {

    const [value, setValue] = useState<SortValues>(SortValues.FOR_RATING)

    console.log(value)

    return (
        <header className={style.container}>
            <div className={style.content}>
                <h1 className={style.title}>{city}</h1>
                <span className={style.desc}>{count} найденных экскурсий</span>
            </div>
            <SelectParams
                defaultValue={SortValues.FOR_POPULAR}
                onChangeValue={setValue}
                options={sortTypesArray}
                placeholder={"Сортировка по"}
                selectType={SelectType.SORT}
            />
        </header>
    );
};