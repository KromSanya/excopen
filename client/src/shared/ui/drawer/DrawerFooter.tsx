import * as React from "react";
import {cn} from "@/app/lib/utils.ts";

export const DrawerFooter = ({
                          className,
                          ...props
                      }: React.HTMLAttributes<HTMLDivElement>) => (
    <div
        className={cn("mt-auto flex flex-col gap-2 pb-4", className)}
        {...props}
    />
)

DrawerFooter.displayName = "DrawerFooter"