import * as React from "react"
import * as SwitchPrimitives from "@radix-ui/react-switch"
import {cn} from "@/app/lib/utils.ts";
import {useEffect, useState} from "react";

type SwitchProps = React.ComponentPropsWithoutRef<typeof SwitchPrimitives.Root> & {
    onChangeValue: (byCity: boolean) => void
}

const Switch = React.forwardRef<
    React.ElementRef<typeof SwitchPrimitives.Root>,
    SwitchProps
>((
    {
      className,
      ...props
    },
    ref
) => {

    const [value, setValue] = useState<boolean>(false)

    useEffect(() => {
        props.onChangeValue(value)
    }, [value, props.onChangeValue, props]);


    return (
        <SwitchPrimitives.Root
            className={cn(
                "bg-grayscale-200 peer inline-flex h-5 w-9 shrink-0 cursor-pointer items-center rounded-full " +
                "border-2 border-transparent shadow-sm " +
                "transition-colors " +
                "focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 " +
                "focus-visible:ring-offset-background " +
                "disabled:cursor-not-allowed disabled:opacity-50 " +
                "data-[state=checked]:bg-primary-100 data-[state=unchecked]:bg-input",
                className
            )}
            checked={value}
            onCheckedChange={(checked) => setValue(checked)}
            {...props}
            ref={ref}
        >
            <SwitchPrimitives.Thumb
                className={cn(
                    "pointer-events-none block h-4 w-4 rounded-full bg-grayscale-0 shadow-lg ring-0 transition-transform data-[state=checked]:translate-x-4 data-[state=unchecked]:translate-x-0"
                )}
            />
        </SwitchPrimitives.Root>
    )
})

Switch.displayName = SwitchPrimitives.Root.displayName

export { Switch }
