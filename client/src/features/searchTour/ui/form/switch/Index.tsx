import {FC} from "react";
import {Orientation} from "@/shared/types";
import {Switch} from "@/shared/ui";
import style from "./style.module.css"
import {useSearchContext} from "@/features";

type SwitchProps = {
    orientation: Orientation
}

export const Index: FC<SwitchProps> = ({orientation}) => {

    const {context, setByCity} = useSearchContext()

    return (
        <div className={orientation === Orientation.HORIZONTAL ? style.horMode : style.verMode}>
            <div className={style.switch}>
                <Switch defaultValueBol={context.searchParams.byCity} onChangeValue={setByCity}/>
                <span>Поиск в городе</span>
            </div>
        </div>
    );
};