import * as React from "react"
import { Command as CommandPrimitive } from "cmdk"
import { cn } from "@/app/lib/utils.ts"

export const CommandGroup = React.forwardRef<
  React.ElementRef<typeof CommandPrimitive.Group>,
  React.ComponentPropsWithoutRef<typeof CommandPrimitive.Group>
>(({className, ...props}, ref) => {

    const baseStyle: string = [
        "overflow-hidden text-md",
        "[&_[cmdk-group-heading]]:text-lg [&_[cmdk-group-heading]]:p-4 [&_[cmdk-group-heading]]:font-medium"
    ].join(" ")

    return (
        <CommandPrimitive.Group ref={ref} className={cn(baseStyle, className)} {...props}/>
    )
})

CommandGroup.displayName = CommandPrimitive.Group.displayName