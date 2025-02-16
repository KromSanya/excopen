import * as React from "react"
import * as PopoverPrimitive from "@radix-ui/react-popover"

import { cn } from "@/app/lib/utils.ts"

export const Popover = PopoverPrimitive.Root
export const PopoverTrigger = PopoverPrimitive.Trigger
export const PopoverAnchor = PopoverPrimitive.Anchor

export const PopoverContent = React.forwardRef<
  React.ElementRef<typeof PopoverPrimitive.Content>,
  React.ComponentPropsWithoutRef<typeof PopoverPrimitive.Content>
>((
    {
        className,
        align = "center",
        sideOffset = 4,
        ...props
    },
    ref
) => {

    const baseStyles: string = [
        "w-auto font-medium shadow-md outline-none data-[state=open]:z-60 data-[state=closed]:-z-10",
        "data-[state=open]:animate-in",
        "data-[state=closed]:animate-out",
        "data-[state=closed]:fade-out-0",
        "data-[state=open]:fade-in-0",
        "data-[state=closed]:zoom-out-95",
        "data-[state=open]:zoom-in-95",
        "data-[side=switch]:slide-in-from-top-4",
        "data-[side=left]:slide-in-from-right-4",
        "data-[side=right]:slide-in-from-left-4",
        "data-[side=top]:slide-in-from-switch-4"
    ].join(" ")

    return (
        <PopoverPrimitive.Portal>
            <PopoverPrimitive.Content
                ref={ref}
                align={align}
                sideOffset={sideOffset}
                className={cn(baseStyles, className)}
                {...props}
            />
        </PopoverPrimitive.Portal>
    )
})

PopoverContent.displayName = PopoverPrimitive.Content.displayName