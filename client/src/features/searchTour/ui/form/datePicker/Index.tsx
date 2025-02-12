import {FC, useEffect, useState} from "react";
import { Calendar, Popover, PopoverContent, PopoverTrigger } from "@/shared/ui";
import { CalendarButton } from "./CalendarButton.tsx";
import {useSearchContext} from "@/features";
import {RangeType} from "@/shared/types";

export const Index: FC = () => {

    const { searchParams, setDate, isSearch } = useSearchContext();
    const [range, setRange] = useState(searchParams.date);

    const [isPopoverOpen, setIsPopoverOpen] = useState<boolean>(false);
    const [isTouched, setIsTouched] = useState<boolean>(false);

    useEffect(() => {
        setDate(range)
    }, [range, setDate]);

    const selectRangeHandler = (selectedRange: RangeType | undefined) => {
        if (selectedRange) setRange(selectedRange)
        if (selectedRange && selectedRange.to) setIsPopoverOpen(false);
    };

    const clearValue = () => {
        setRange({ from: undefined, to: undefined });
    };

    const clickHandler = () => {
        setIsTouched(true);
        setIsPopoverOpen(true);
    };

    return (
        <Popover open={isPopoverOpen} onOpenChange={setIsPopoverOpen}>
            <PopoverTrigger role={"form-datePicker"} asChild>
                <CalendarButton
                    isSearch={isSearch}
                    range={range}
                    isTouched={isTouched}
                    onClick={clickHandler}
                    onClear={clearValue}
                />
            </PopoverTrigger>
            <PopoverContent side={"bottom"}>
                <Calendar
                    defaultMonth={range.to}
                    mode="range"
                    selected={range}
                    onSelect={selectRangeHandler}
                    initialFocus
                    disabled={date => date < new Date() || date < new Date("1900-01-01")}
                />
            </PopoverContent>
        </Popover>
    );
};