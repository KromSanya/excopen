import * as React from "react";
import * as SelectPrimitive from "@radix-ui/react-select";
import {cn} from "@/app/lib/utils.ts";
import {ChevronDownIcon} from "@radix-ui/react-icons";

const SelectScrollDownButton = React.forwardRef<
    React.ElementRef<typeof SelectPrimitive.ScrollDownButton>,
    React.ComponentPropsWithoutRef<typeof SelectPrimitive.ScrollDownButton>
>((
    {
        className,
        ...props
    },
    ref
) => (
    <SelectPrimitive.ScrollDownButton
        ref={ref}
        className={cn(
            "flex cursor-default items-center justify-center py-1",
            className
        )}
        {...props}
    >
        <ChevronDownIcon className="h-4 w-4" />
    </SelectPrimitive.ScrollDownButton>
))

SelectScrollDownButton.displayName = SelectPrimitive.ScrollDownButton.displayName

export {SelectScrollDownButton}