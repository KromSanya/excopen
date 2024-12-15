import * as React from "react"
import { Slot } from "@radix-ui/react-slot"
import { cva, type VariantProps } from "class-variance-authority"

import { cn } from "@/app/lib/utils.ts"

const CalendarButtonVariants = cva(
  "inline-flex items-center justify-center rounded-md text-sm font-medium transition-colors",
  {
    variants: {
      variant: {
        default: "hover:bg-primary-100",
        ghost: "bg-grayscale-0 text-grayscale-500 hover:bg-grayscale-200",
      }
    },
    defaultVariants: {
      variant: "default",
    },
  }
)

export interface CalendarButtonProps
  extends React.ButtonHTMLAttributes<HTMLButtonElement>,
    VariantProps<typeof CalendarButtonVariants> {
  asChild?: boolean
}

const CalendarButton = React.forwardRef<HTMLButtonElement, CalendarButtonProps>(
  ({ className, variant, asChild = false, ...props }, ref) => {
    const Comp = asChild ? Slot : "button"
    return (
      <Comp
        className={cn(CalendarButtonVariants({ variant, className }))}
        ref={ref}
        {...props}
      />
    )
  }
)
CalendarButton.displayName = "CalendarButton"

export { CalendarButton, CalendarButtonVariants }