import * as React from "react";
import * as DropdownMenuPrimitive from "@radix-ui/react-dropdown-menu";
import {cn} from "@/app/lib/utils.ts";

const LocationDropdownContent = React.forwardRef<
    React.ElementRef<typeof DropdownMenuPrimitive.Content>,
    React.ComponentPropsWithoutRef<typeof DropdownMenuPrimitive.Content>
>(({ className, sideOffset = 4, ...props }, ref) => (
    <DropdownMenuPrimitive.Portal>
        <DropdownMenuPrimitive.Content
            ref={ref}
            sideOffset={sideOffset}
            className={cn(
                "z-50 w-64 overflow-hidden px-2 rounded-lg border bg-grayscale-0 text-grayscale-500 text-sm font-medium",
                className
            )}
            {...props}
        />
    </DropdownMenuPrimitive.Portal>
))

LocationDropdownContent.displayName = DropdownMenuPrimitive.Content.displayName

export {LocationDropdownContent}