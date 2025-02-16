import * as React from "react";
import * as DropdownMenuPrimitive from "@radix-ui/react-dropdown-menu";
import {cn} from "@/app/lib/utils.ts";

const LocationDropdownItem = React.forwardRef<
    React.ElementRef<typeof DropdownMenuPrimitive.Item>,
    React.ComponentPropsWithoutRef<typeof DropdownMenuPrimitive.Item>
>((
    {
        className,
        ...props
    },
    ref
) => {

    return (
        <DropdownMenuPrimitive.Item
            ref={ref}
            className={cn(
                "h-8 relative flex cursor-default items-center gap-2 px-2 outline-none transition-colors hover:text-grayscale-400",
                className
            )}
            {...props}
        />
    )
})
LocationDropdownItem.displayName = DropdownMenuPrimitive.Item.displayName

export {LocationDropdownItem}