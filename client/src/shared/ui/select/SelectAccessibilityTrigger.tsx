import * as React from "react";
import * as SelectPrimitive from "@radix-ui/react-select";
import {cn} from "@/app/lib/utils.ts";
import {SelectLabel} from "./SelectLabel.tsx";
import {SelectGroup} from "./SelectGroup.tsx";

export const SelectAccessibilityTrigger = React.forwardRef<
    React.ElementRef<typeof SelectPrimitive.Trigger>,
    React.ComponentPropsWithoutRef<typeof SelectPrimitive.Trigger> & {
        icon: string;
        placeholder: string;
        isSearch: boolean;
    }
>((
    {
        className,
        placeholder,
        icon,
        isSearch,
        value,
        ...props
    },
    ref
) => {

    const containerStyles: string = [
        "flex flex-col relative rounded-xl",
        !value && isSearch && "border border-secondary-red"
    ].join(" ")

    const iconStyles: string = "h-5 w-5 mr-3"

    const placeholderStyles: string = [
        "absolute left-14 transition-all duration-300",
        "text-base text-grayscale-400 cursor-pointer",
        value ? "top-2" : "top-4"
    ].join(" ")

    const triggerStyles: string = [
        "flex items-center data-[state=open]:bg-grayscale-0",
        "transition-all duration-300 rounded-xl outline-none",
        "data-[state=open]:border data-[state=open]:border-grayscale-400",
        "justify-start h-14 w-full wide:w-72 px-6 py-4 text-base transition-all duration-300",
        !value && isSearch
            ? "bg-red-100 data-[state=open]:border-secondary-red"
            : "bg-grayscale-200 hover:bg-grayscale-300"
    ].join(" ")

    return (
        <div className={containerStyles}>
            <SelectGroup>
                <SelectLabel className={placeholderStyles}>
                    {placeholder}
                </SelectLabel>
            </SelectGroup>
            <SelectPrimitive.Trigger ref={ref} className={cn(triggerStyles, className)} {...props}>
                <img alt={"icon"} src={icon} className={iconStyles}/>
                <SelectGroup>
                    <SelectLabel className={"mt-3"}>
                        {value}
                    </SelectLabel>
                </SelectGroup>
            </SelectPrimitive.Trigger>
        </div>
    )
});

SelectAccessibilityTrigger.displayName = SelectPrimitive.Trigger.displayName;