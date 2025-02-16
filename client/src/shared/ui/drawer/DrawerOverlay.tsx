import * as React from "react";
import {Drawer as DrawerPrimitive} from "vaul";
import {cn} from "@/app/lib/utils.ts";

export const DrawerOverlay = React.forwardRef<
    React.ElementRef<typeof DrawerPrimitive.Overlay>,
    React.ComponentPropsWithoutRef<typeof DrawerPrimitive.Overlay>
>(({ className, ...props }, ref) => (
    <DrawerPrimitive.Overlay
        ref={ref}
        className={cn("fixed inset-0 bg-grayscale-600/80", className)}
        {...props}
    />
))

DrawerOverlay.displayName = DrawerPrimitive.Overlay.displayName