import * as React from "react";
import {cn} from "@/app/lib/utils.ts";

const BreadcrumbList = React.forwardRef<
    HTMLOListElement,
    React.ComponentPropsWithoutRef<"ol">
>(({ className, ...props }, ref) => (
    <ol
        ref={ref}
        className={cn(
            "flex flex-wrap items-center gap-2 break-words text-grayscale-400 text-sm pt-4",
            className
        )}
        {...props}
    />
))

BreadcrumbList.displayName = "BreadcrumbList"

export {BreadcrumbList}