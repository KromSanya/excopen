import * as React from "react";
import { Command as CommandPrimitive } from "cmdk";
import { cn } from "@/app/lib/utils.ts";
import {ComponentPropsWithoutRef, ElementRef} from "react";

const CommandInput = React.forwardRef<
    ElementRef<typeof CommandPrimitive.Input>,
    ComponentPropsWithoutRef<typeof CommandPrimitive.Input>
>(({ className, ...props }, ref) => {

    return (
        <CommandPrimitive.Input
            ref={ref}
            className={cn(
                "flex h-14 w-72 bg-grayscale-200 rounded-xl px-6 py-4 text-base " +
                "placeholder:text-base placeholder:text-grayscale-400 " +
                "focus:bg-white focus:ring-1 focus:ring-black focus:outline-none transition-colors ",
                className
            )}
            {...props}
        />
    );
});

CommandInput.displayName = CommandPrimitive.Input.displayName;

export { CommandInput };