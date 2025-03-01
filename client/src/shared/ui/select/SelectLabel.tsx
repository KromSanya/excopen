import * as React from "react";
import * as SelectPrimitive from "@radix-ui/react-select";
import {cn} from "@/app/lib/utils.ts";

export const SelectLabel = React.forwardRef<
    React.ElementRef<typeof SelectPrimitive.Label>,
    React.ComponentPropsWithoutRef<typeof SelectPrimitive.Label>
>((
    { className, ...props },
    ref
) => (
    <SelectPrimitive.Label ref={ref} className={cn("truncate", className)}{...props}/>
))

SelectLabel.displayName = SelectPrimitive.Label.displayName