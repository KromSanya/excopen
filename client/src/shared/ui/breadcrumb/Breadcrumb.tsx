import * as React from "react";

export const Breadcrumb = React.forwardRef<
    HTMLElement,
    React.ComponentPropsWithoutRef<"nav"> & {
        separator?: React.ReactNode
    }
>(({ ...props }, ref) =>
    <nav className={"w-full huge:w-[1440px]"} ref={ref} aria-label="breadcrumb" {...props} />
)

Breadcrumb.displayName = "Breadcrumb"