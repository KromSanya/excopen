import * as React from "react";
import {Command as CommandPrimitive} from "cmdk";
import {cn} from "@/app/lib/utils.ts";

const CommandList = React.forwardRef<
    React.ElementRef<typeof CommandPrimitive.List>,
    React.ComponentPropsWithoutRef<typeof CommandPrimitive.List>
>(({ className, ...props }, ref) => (
    <CommandPrimitive.List
        ref={ref}
        className={
            cn(
                "max-h-[300px] w-full bg-grayscale-0 mt-2 rounded-md border overflow-y-auto overflow-x-hidden",
                className
            )
    }
        {...props}
    />
))

CommandList.displayName = CommandPrimitive.List.displayName

export {CommandList}