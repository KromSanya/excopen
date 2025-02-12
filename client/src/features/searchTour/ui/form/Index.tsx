import {FC} from "react";
import {Orientation} from "@/shared/types";
import {SearchButton} from "./button";
import {Input} from "./input"
import {DatePicker} from "./datePicker";
import {Select} from "./select";
import {Switch} from "./switch";
import style from "./style.module.css"
import {cn} from "@/app/lib/utils.ts";
import {useWindowSize} from "usehooks-ts";
import {useSearchContext} from "@/features";

type FormProps = {
    orientation: Orientation
}

export const Index: FC<FormProps> = ({orientation}) => {

    const {width} = useWindowSize()

    const {searchParams} = useSearchContext()

    const disabled = !!searchParams.location
        && !!searchParams.date.from
        && !!searchParams.date.to
        && !!searchParams.accessibility
    
    return (
        <div
            role={"form"}
            className={cn(
                style.container,
                orientation === Orientation.HORIZONTAL ? style.containerHorMode : style.containerVerMode
            )}
        >
            <div
                className={cn(
                    style.header,
                    orientation === Orientation.HORIZONTAL ? style.headerHorMode : style.headerVerMode
                )}>
                {
                    orientation === Orientation.VERTICAL &&
                    <div className={style.heading}>
                        <h3>Куда теперь?</h3>
                    </div>
                }
                <Input/>
                <DatePicker/>
                <Select/>
                {width < 1440 && <Switch orientation={orientation}/>}
                <SearchButton location={searchParams.location} orientation={orientation} disabled={disabled}/>
            </div>
            {orientation === Orientation.HORIZONTAL && width > 1440 && <Switch orientation={orientation}/>}
        </div>
    );
};