import * as React from "react"
import * as SwitchPrimitives from "@radix-ui/react-switch"
import {cn} from "@/app/lib/utils.ts";
import {useEffect, useState} from "react";

export const Switch = React.forwardRef<
    React.ElementRef<typeof SwitchPrimitives.Root>,
    React.ComponentPropsWithoutRef<typeof SwitchPrimitives.Root> & {
        defaultValueBol: boolean
        onChangeValue: (byCity: boolean) => void
    }
>((
    {className, defaultValueBol, onChangeValue, ...props},
    ref
) => {

    const [value, setValue] = useState<boolean>(defaultValueBol)

    useEffect(() => {
        onChangeValue(value)
    }, [value, onChangeValue]);

    const baseStyles: string = [
        "bg-grayscale-200 peer inline-flex h-5 w-9 shrink-0 cursor-pointer items-center rounded-full",
        "border-2 border-transparent shadow-sm transition-colors",
        "focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2",
        "focus-visible:ring-offset-background disabled:cursor-not-allowed",
        "data-[state=checked]:bg-primary-100 data-[state=unchecked]:bg-inputs disabled:opacity-50"
    ].join(" ")

    const trumbStyles: string = [
        "pointer-events-none block h-4 w-4 rounded-full bg-grayscale-0 shadow-lg ring-0",
        "transition-transform data-[state=checked]:translate-x-4 data-[state=unchecked]:translate-x-0"
    ].join(" ")

    return (
        <SwitchPrimitives.Root
            className={cn(baseStyles, className)}
            checked={value}
            onCheckedChange={(checked) => setValue(checked)}
            {...props}
            ref={ref}
        >
            <SwitchPrimitives.Thumb className={cn(trumbStyles)}/>
        </SwitchPrimitives.Root>
    )
})

Switch.displayName = SwitchPrimitives.Root.displayName