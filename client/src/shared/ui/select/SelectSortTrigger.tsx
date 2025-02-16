import * as React from "react";
import * as SelectPrimitive from "@radix-ui/react-select";
import {cn} from "@/app/lib/utils.ts";
import {ChevronDownIcon} from "@radix-ui/react-icons";
import {SelectLabel} from "./SelectLabel.tsx";
import {SelectGroup} from "./SelectGroup.tsx";

export const SelectSortTrigger = React.forwardRef<
    React.ElementRef<typeof SelectPrimitive.Trigger>,
    React.ComponentPropsWithoutRef<typeof SelectPrimitive.Trigger> & {
        placeholder: string;
    }
>((
    {
        className,
        placeholder,
        value,
        ...props
    },
    ref
) => {

    const containerStyles: string = [
        "flex flex-col relative rounded-xl"
    ].join(" ")

    const triggerStyles: string = [
        "flex items-center data-[state=open]:bg-grayscale-0",
        "transition-all duration-300 rounded-xl outline-none",
        "data-[state=open]:border data-[state=open]:border-grayscale-400",
        "justify-between h-10 w-40 bg-grayscale-0 px-3 py-2 text-sm"
    ].join(" ")

    const iconStyles: string = "h-4 w-4"

    return (
        <div className={containerStyles}>
            <SelectPrimitive.Trigger ref={ref} className={cn(triggerStyles, className)} {...props}>
                <SelectGroup>
                    <SelectLabel>
                        {!value ? placeholder : value}
                    </SelectLabel>
                </SelectGroup>
                <SelectPrimitive.Icon asChild>
                    <ChevronDownIcon className={iconStyles}/>
                </SelectPrimitive.Icon>
            </SelectPrimitive.Trigger>
        </div>
    )
});

SelectSortTrigger.displayName = SelectPrimitive.Trigger.displayName;