import * as React from "react";
import {Drawer as DrawerPrimitive} from "vaul";
import {cn} from "@/app/lib/utils.ts";
import {DrawerOverlay} from "./DrawerOverlay.tsx";
import {DrawerPortal} from "./DrawerPortal.tsx";

export const DrawerContent = React.forwardRef<
    React.ElementRef<typeof DrawerPrimitive.Content>,
    React.ComponentPropsWithoutRef<typeof DrawerPrimitive.Content>
>(({ className, children, ...props }, ref) => (
    <DrawerPortal>
        <DrawerOverlay />
        <DrawerPrimitive.Content
            ref={ref}
            className={cn(
                "fixed inset-x-0 bottom-0 z-50 mt-24 flex h-auto flex-col rounded-t-xl bg-grayscale-0",
                className
            )}
            {...props}
        >
            <div className="mx-auto mt-4 h-2 w-[100px] rounded-full bg-muted" />
            {children}
        </DrawerPrimitive.Content>
    </DrawerPortal>
))

DrawerContent.displayName = "DrawerContent"