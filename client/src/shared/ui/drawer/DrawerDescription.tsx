import * as React from "react";
import {Drawer as DrawerPrimitive} from "vaul";
import {cn} from "@/app/lib/utils.ts";

export const DrawerDescription = React.forwardRef<
    React.ElementRef<typeof DrawerPrimitive.Description>,
    React.ComponentPropsWithoutRef<typeof DrawerPrimitive.Description>
>(({ className, ...props }, ref) => (
    <DrawerPrimitive.Description
        ref={ref}
        className={cn("text-sm text-muted-foreground", className)}
        {...props}
    />
))

DrawerDescription.displayName = DrawerPrimitive.Description.displayName