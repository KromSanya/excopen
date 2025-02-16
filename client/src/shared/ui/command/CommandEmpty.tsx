import * as React from "react";
import {Command as CommandPrimitive} from "cmdk";

export const CommandEmpty = React.forwardRef<
    React.ElementRef<typeof CommandPrimitive.Empty>,
    React.ComponentPropsWithoutRef<typeof CommandPrimitive.Empty>
>((props, ref) => (
    <CommandPrimitive.Empty ref={ref} className="py-4 text-center text-md" {...props}/>
))

CommandEmpty.displayName = CommandPrimitive.Empty.displayName