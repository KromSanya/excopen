import * as React from "react";
import {Drawer as DrawerPrimitive} from "vaul";
import {cn} from "@/app/lib/utils.ts";

export const DrawerTitle = React.forwardRef<
    React.ElementRef<typeof DrawerPrimitive.Title>,
    React.ComponentPropsWithoutRef<typeof DrawerPrimitive.Title>
>((
    {
        className,
        ...props
    },
    ref
) => (
    <DrawerPrimitive.Title
        ref={ref}
        className={cn("w-full px-6 pt-4 font-medium text-xl text-grayscale-500", className)}
        {...props}
    />
))

DrawerTitle.displayName = DrawerPrimitive.Title.displayName