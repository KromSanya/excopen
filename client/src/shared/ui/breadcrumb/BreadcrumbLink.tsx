import * as React from "react";
import {Slot} from "@radix-ui/react-slot";
import {cn} from "@/app/lib/utils.ts";

const BreadcrumbLink = React.forwardRef<
    HTMLAnchorElement,
    React.ComponentPropsWithoutRef<"a"> & {
    asChild?: boolean
}
>(({ asChild, className, ...props }, ref) => {
    const Comp = asChild ? Slot : "a"

    return (
        <Comp
            ref={ref}
            className={cn("transition-colors hover:text-grayscale-500", className)}
            {...props}
        />
    )
})

BreadcrumbLink.displayName = "BreadcrumbLink"

export {BreadcrumbLink}