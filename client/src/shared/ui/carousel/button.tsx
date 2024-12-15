import * as React from "react"
import { Slot } from "@radix-ui/react-slot"
import { cva, type VariantProps } from "class-variance-authority"

import { cn } from "@/app/lib/utils.ts"

const carouselButtonVariants = cva(
  "inline-flex items-center justify-center gap-2 whitespace-nowrap rounded-md text-sm font-medium transition-colors focus-visible:outline-none focus-visible:ring-1 focus-visible:ring-ring disabled:pointer-events-none [&_svg]:shrink-0",
  {
    variants: {
      variant: {
        default: "bg-grayscale-600 hover:bg-grayscale-500"
      },
      size: {default: "h-9 px-4 py-2"}
    },
    defaultVariants: {
      variant: "default",
      size: "default",
    },
  }
)

export interface CarouselButtonProps
  extends React.ButtonHTMLAttributes<HTMLButtonElement>,
    VariantProps<typeof carouselButtonVariants> {
  asChild?: boolean
}

const CarouselButton = React.forwardRef<HTMLButtonElement, CarouselButtonProps>(
  ({ className, variant, size, asChild = false, ...props }, ref) => {
    const Comp = asChild ? Slot : "button"
    return (
      <Comp
        className={cn(carouselButtonVariants({ variant, size, className }))}
        ref={ref}
        {...props}
      />
    )
  }
)

CarouselButton.displayName = "CarouselButton"

export { CarouselButton, carouselButtonVariants }
