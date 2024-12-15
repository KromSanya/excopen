import * as React from "react";
import {cn} from "@/app/lib/utils.ts";
import {ChevronRightIcon} from "@radix-ui/react-icons";

const BreadcrumbSeparator = (
    {
        children,
        className,
        ...props
    }: React.ComponentProps<"li">
) => (
        <li
            role="presentation"
            aria-hidden="true"
            className={cn("[&>svg]:w-4 [&>svg]:h-4", className)}
            {...props}
        >
            {children ?? <ChevronRightIcon />}
        </li>
)

BreadcrumbSeparator.displayName = "BreadcrumbSeparator"

export {BreadcrumbSeparator}