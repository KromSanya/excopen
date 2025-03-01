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

    const {context} = useSearchContext()

    const disabled = !!context.searchParams.location
        && !!context.searchParams.date.from
        && !!context.searchParams.date.to
        && !!context.searchParams.accessibility
    
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
                {width < 1440 || orientation === Orientation.VERTICAL && <Switch orientation={orientation}/>}
                <SearchButton location={context.searchParams.location} orientation={orientation} disabled={disabled}/>
            </div>
            {orientation === Orientation.HORIZONTAL && width > 1440 && <Switch orientation={orientation}/>}
        </div>
    );
};