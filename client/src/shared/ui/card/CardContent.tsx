import * as React from "react";
import {cn} from "@/app/lib/utils.ts";

const CardContent = React.forwardRef<
    HTMLDivElement,
    React.HTMLAttributes<HTMLDivElement>
>(({ className, ...props }, ref) => (
    <div ref={ref} className={cn("p-6 pt-0 flex aspect-square items-center justify-center", className)} {...props} />
))

CardContent.displayName = "CardContent"

export {CardContent}