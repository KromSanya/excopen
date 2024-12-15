import * as React from "react"
import { Slot } from "@radix-ui/react-slot"
import { cva, type VariantProps } from "class-variance-authority"
import {cn} from "@/app/lib/utils.ts";

const ButtonVariants = cva(
  "inline-flex items-center justify-center gap-2 " +
    "whitespace-nowrap rounded-md text-base " +
    "transition-colors " +
    "focus-visible:outline-none focus-visible:ring-1 focus-visible:ring-ring " +
    "disabled:pointer-events-none disabled:opacity-50 " +
    "[&_svg]:pointer-events-none [&_svg]:size-4 [&_svg]:shrink-0",
  {
    variants: {
      variant: {
        default: "bg-primary-0 text-grayscale-500 hover:bg-primary-100",
        outline: "border border-grayscale-400 bg-transparent text-grayscale-400 hover:border-grayscale-500 hover:text-grayscale-500",
        secondary: "bg-grayscale-300 text-grayscale-500 hover:opacity-90 flex justify-between",
          calendar: "flex justify-start bg-grayscale-200 text-base font-light rounded-xl px-6 py-4 data-[state=open]:bg-grayscale-0 data-[state=open]:border data-[state=open]:border-grayscale-600"
      },
      size: {
          default: "h-12 px-10 rounded-xl",
          hg: "h-14 px-8",
          lg: "h-14 w-72 rounded-xl",
          normal: "h-12 px-4",
          md: "h-12 px-8 rounded-xl",
          sm: "h-8 px-4",
          smIcon: "h-10 w-10",
          lgIcon: "h-14 w-14 rounded-xl",
          calendar: "h-14 w-72",
          roundedIcon: "h-14 w-14 rounded-full"
      },
    },
    defaultVariants: {
      variant: "default",
      size: "default",
    },
  }
)

export interface ButtonProps
  extends React.ButtonHTMLAttributes<HTMLButtonElement>, VariantProps<typeof ButtonVariants> {
    asChild?: boolean
}

const Button = React.forwardRef<HTMLButtonElement, ButtonProps>(
  ({ className, variant, size, asChild = false, ...props }, ref) => {
    const Comp = asChild ? Slot : "button"
    return (
      <Comp
        className={cn(ButtonVariants({ variant, size, className }))}
        ref={ref}
        {...props}
      />
    )
  }
)

Button.displayName = "Button"

export { Button, ButtonVariants }