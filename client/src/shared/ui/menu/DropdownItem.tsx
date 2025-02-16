import * as React from "react";
import * as DropdownMenuPrimitive from "@radix-ui/react-dropdown-menu";
import {cn} from "@/app/lib/utils.ts";
import {useNavigate} from "react-router-dom";

const DropdownMenuItem = React.forwardRef<
    React.ElementRef<typeof DropdownMenuPrimitive.Item>,
    React.ComponentPropsWithoutRef<typeof DropdownMenuPrimitive.Item> & {
        path: string
    }
>((
    {
        className,
        path ,
        ...props
    },
    ref
) => {

    const navigate = useNavigate()
    const clickHandle = (value: string) => navigate(value)

    return (
        <DropdownMenuPrimitive.Item
            ref={ref}
            className={cn(
                "h-10 relative flex cursor-default items-center gap-2 px-2 outline-none transition-colors hover:text-grayscale-400",
                className
            )}
            onClick={() => clickHandle(path)}
            {...props}
        />
    )
})
DropdownMenuItem.displayName = DropdownMenuPrimitive.Item.displayName

export {DropdownMenuItem}