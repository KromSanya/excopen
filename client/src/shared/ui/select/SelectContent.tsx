import * as React from "react"
import * as SelectPrimitive from "@radix-ui/react-select"
import {cn} from "@/app/lib/utils.ts";

export const SelectContent = React.forwardRef<
    React.ElementRef<typeof SelectPrimitive.Content>,
    React.ComponentPropsWithoutRef<typeof SelectPrimitive.Content>
>((
    {
        className,
        children,
        position = "popper",
        ...props
    },
    ref
) => {

    const contentStyles: string = [
        "relative z-50 overflow-hidden rounded-md",
        "border bg-grayscale-0 shadow-md data-[state=open]:animate-in",
        "data-[state=closed]:animate-out data-[state=closed]:fade-out-0 data-[state=open]:fade-in-0",
        "data-[side=switch]:slide-in-from-top-2",
        "data-[side=top]:slide-in-from-switch-2"
    ].join(" ")

    const popperStyles: string = [
        "h-[var(--radix-select-trigger-height)] w-full min-w-[var(--radix-select-trigger-width)]"
    ].join(" ")

    return (
        <SelectPrimitive.Portal>
            <SelectPrimitive.Content ref={ref} className={cn(contentStyles, className)} position={position} {...props}>
                <SelectPrimitive.Viewport className={cn("p-1", position === "popper" && popperStyles)}>
                    {children}
                </SelectPrimitive.Viewport>
            </SelectPrimitive.Content>
        </SelectPrimitive.Portal>
    )
})

SelectContent.displayName = SelectPrimitive.Content.displayName