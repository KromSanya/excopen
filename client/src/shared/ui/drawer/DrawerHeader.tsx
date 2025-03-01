import * as React from "react";
import {cn} from "@/app/lib/utils.ts";

export const DrawerHeader = ({className, ...props}: React.HTMLAttributes<HTMLDivElement>) => (
    <div
        className={cn("px-2", className)}
        {...props}
    />
)

DrawerHeader.displayName = "DrawerHeader"