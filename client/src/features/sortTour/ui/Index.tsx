import {FC, useEffect, useState} from 'react';
import {Select, SelectContent, SelectItem, SelectSortTrigger} from "@/shared/ui";
import {sortTypesArray} from "@/features/sortTour/config";

type SelectProps = {
    sortValue: string
    setSortValue: (value: string) => void
}

export const Index: FC<SelectProps> = ({sortValue, setSortValue}) => {

    const defaultValue = sortTypesArray[0]
    const [value, setValue] = useState<string>(sortValue)
    const label = sortTypesArray.find(
        opt => opt.value === value
    )?.label

    useEffect(() => {
        setSortValue(value)
    }, [setSortValue, value]);

    return (
        <Select value={value} onValueChange={setValue}>
            <SelectSortTrigger value={label} placeholder={defaultValue.label}/>
            <SelectContent side={"bottom"}>
                {sortTypesArray.map((tour) =>
                    <SelectItem key={tour.value} value={String(tour.value)}>
                        {tour.label}
                    </SelectItem>
                )}
            </SelectContent>
        </Select>
    );

};