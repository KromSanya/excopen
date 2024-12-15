import { useState } from "react";
import {Select} from "@/shared/ui/select/Select.tsx";
import {SelectTrigger} from "@/shared/ui/select/SelectTrigger.tsx";
import {SelectValue} from "@/shared/ui/select/SelectValue.tsx";
import {SelectContent} from "@/shared/ui/select/SelectContent.tsx";
import {SelectItem} from "@/shared/ui/select/SelectItem.tsx";
import {SelectType} from "@/shared/ui";

type SelectParamsProps<T> = {
    defaultValue?: T
    onChangeValue: (value: T) => void
    options: {value: T, label: string}[]
    placeholder: string
    selectType: SelectType
};

export const SelectParams = <T,>(
    {defaultValue, onChangeValue, options, placeholder, selectType} : SelectParamsProps<T>
) => {

    const [
        value,
        setValue
    ] = useState<T | undefined>(defaultValue);

    const clickHandler = (val: string) => {

        const selectedOption = options.find(
            (option) =>
                String(option.value) === val
        )

        if (selectedOption) setValue(selectedOption.value);
        if (value !== undefined) onChangeValue(value);

    }

    return (
        <Select value={String(value)} onValueChange={clickHandler}>
            <SelectTrigger selectType={selectType}>
                <SelectValue placeholder={placeholder} />
            </SelectTrigger>
            <SelectContent side={"bottom"}>
                {options.map((tour, key) =>
                    <SelectItem key={key} value={String(tour.value)}>
                        {tour.label}
                    </SelectItem>
                )}
            </SelectContent>
        </Select>
    );
};