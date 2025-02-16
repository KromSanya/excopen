import * as React from "react"
import * as DropdownMenuPrimitive from "@radix-ui/react-dropdown-menu"
import {cn} from "@/app/lib/utils.ts";

const LocationDropdownSeparator = React.forwardRef<
    React.ElementRef<typeof DropdownMenuPrimitive.Separator>,
    React.ComponentPropsWithoutRef<typeof DropdownMenuPrimitive.Separator>
>(({ className, ...props }, ref) => (
    <DropdownMenuPrimitive.Separator
        ref={ref}
        className={cn("-mx-1 my-1 h-px bg-grayscale-200", className)}
        {...props}
    />
))
LocationDropdownSeparator.displayName = DropdownMenuPrimitive.Separator.displayName

export {LocationDropdownSeparator}