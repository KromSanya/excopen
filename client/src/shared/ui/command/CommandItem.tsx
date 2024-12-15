import * as React from "react";
import {Command as CommandPrimitive} from "cmdk";
import {cn} from "@/app/lib/utils.ts";

const CommandItem = React.forwardRef<
    React.ElementRef<typeof CommandPrimitive.Item>,
    React.ComponentPropsWithoutRef<typeof CommandPrimitive.Item>
>(({ className, ...props }, ref) => (
    <CommandPrimitive.Item
        ref={ref}
        className={cn(
            "relative flex cursor-default gap-2 select-none items-center rounded-sm px-2 py-1.5 text-md outline-none",
            className
        )}
        {...props}
    />
))

CommandItem.displayName = CommandPrimitive.Item.displayName

export {CommandItem}