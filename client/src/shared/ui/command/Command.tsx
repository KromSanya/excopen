import * as React from "react";
import {Command as CommandPrimitive} from "cmdk";
import {cn} from "@/app/lib/utils.ts";

export const Command = React.forwardRef<
    React.ElementRef<typeof CommandPrimitive>,
    React.ComponentPropsWithoutRef<typeof CommandPrimitive>
>(({className, ...props}, ref) => (
    <CommandPrimitive ref={ref} className={cn(className)} {...props}/>
))

Command.displayName = CommandPrimitive.displayName