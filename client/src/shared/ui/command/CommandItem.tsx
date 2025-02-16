import * as React from "react";
import {Command as CommandPrimitive} from "cmdk";
import {cn} from "@/app/lib/utils.ts";

export const CommandItem = React.forwardRef<
    React.ElementRef<typeof CommandPrimitive.Item>,
    React.ComponentPropsWithoutRef<typeof CommandPrimitive.Item>
>((
    {
        className,
        ...props
    },
    ref
) => {

    const baseStyle = [
        "flex flex-row gap-2",
        "hover:bg-grayscale-200 transition duration-300",
        "relative cursor-default py-2 px-4 select-none rounded-sm outline-none"
    ].join(" ")

    return (
        <CommandPrimitive.Item
            ref={ref}
            className={cn(baseStyle, className)}
            {...props}
        />
    )
})

CommandItem.displayName = CommandPrimitive.Item.displayName