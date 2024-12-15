import {FC, useEffect, useState} from "react";
import {Button, Calendar, Popover, PopoverContent, PopoverTrigger} from "@/shared/ui";
import {format} from "date-fns";
import {ru} from "date-fns/locale";

type DatePickerProps = {
    onChange: (date: Date | undefined) => void
}

export const DatePicker: FC<DatePickerProps> = ({onChange}) => {

    const [date, setDate] = useState<Date | undefined>(undefined)

    useEffect(() => {
        onChange(date)
    }, [date, onChange]);

    return (
        <Popover>
            <PopoverTrigger asChild>
                <Button variant={"calendar"} size={"calendar"}>
                    {date ? format(date, "PPP", {locale: ru}) : <span className={"text-grayscale-400"}>Когда</span>}
                </Button>
            </PopoverTrigger>
            <PopoverContent className="w-auto">
                <Calendar
                    mode="single"
                    selected={date}
                    onSelect={setDate}
                    initialFocus
                />
            </PopoverContent>
        </Popover>

    );
};