import * as React from "react";
import * as SelectPrimitive from "@radix-ui/react-select";
import {cn} from "@/app/lib/utils.ts";

export const SelectItem = React.forwardRef<
    React.ElementRef<typeof SelectPrimitive.Item>,
    React.ComponentPropsWithoutRef<typeof SelectPrimitive.Item>
>((
    {className, children, ...props},
    ref
) => {

    const itemStyles: string = [
        "hover:bg-grayscale-200 transition-colors relative flex w-full cursor-default",
        "select-none items-center rounded-md py-2 pl-2 text-base outline-none"
    ].join(" ")

    return (
        <SelectPrimitive.Item ref={ref} className={cn(itemStyles, className)}{...props}>
            <SelectPrimitive.ItemText>{children}</SelectPrimitive.ItemText>
        </SelectPrimitive.Item>
    )
})

SelectItem.displayName = SelectPrimitive.Item.displayName