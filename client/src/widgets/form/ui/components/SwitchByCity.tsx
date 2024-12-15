import {FC} from 'react';
import {Switch} from "@/shared/ui";

type SwitchByCityProps = {
    onChangeValue: (byCity: boolean) => void
}

export const SwitchByCity: FC<SwitchByCityProps> = ({onChangeValue}) => {
    return (
        <div className={"flex flex-row gap-2 text-grayscale-600"}>
            <Switch onChangeValue={onChangeValue}/>
            <span>Поиск в городе</span>
        </div>
    );
};