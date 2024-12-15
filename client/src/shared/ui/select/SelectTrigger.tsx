import * as React from "react";
import * as SelectPrimitive from "@radix-ui/react-select";
import {cn} from "@/app/lib/utils.ts";
import {SelectType} from "@/shared/ui";

type SelectTriggerProps = React.ComponentPropsWithoutRef<typeof SelectPrimitive.Trigger> & {
    selectType: SelectType
}

const SelectTrigger = React.forwardRef<
    React.ElementRef<typeof SelectPrimitive.Trigger>,
    SelectTriggerProps
>((
    {
        className,
        children,
        selectType,
        ...props
    },
    ref
) => (
    <SelectPrimitive.Trigger
        ref={ref}
        className={cn(
            "flex items-center justify-between whitespace-nowrap text-grayscale-600",
            "data-[state=open]:bg-grayscale-0 data-[state=open]:border data-[state=open]:border-grayscale-600",
            "transition-colors rounded-xl ",
            selectType === SelectType.ACCESSIBILITY
                ?
                "h-14 w-72 bg-grayscale-200 px-6 py-4 text-base "
                :
                "h-10 w-40 bg-grayscale-200 px-3 py-2 text-sm",
            className
        )}
        {...props}
    >
        {children}
    </SelectPrimitive.Trigger>
))

SelectTrigger.displayName = SelectPrimitive.Trigger.displayName

export {SelectTrigger}