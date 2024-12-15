import * as React from "react"
import { cn } from "@/app/lib/utils.ts"
import { CarouselButton } from "@/shared/ui/carousel/button.tsx"
import { ArrowRightIcon } from "@radix-ui/react-icons"
import {useCarousel} from "@/shared/ui/carousel/hooks.ts";

const CarouselNext = React.forwardRef<
  HTMLButtonElement,
  React.ComponentProps<typeof CarouselButton>
>(({ className, variant, size, ...props }, ref) => {
  const { orientation, scrollNext, canScrollNext } = useCarousel()

  return (
    <CarouselButton
      ref={ref}
      variant={variant}
      size={size}
      className={cn(
        "absolute h-8 w-8 rounded-full",
          "opacity-0 group-hover:opacity-100 transition-opacity duration-300",
          "h-8 w-8 rounded-full bg-black/50 hover:bg-black text-white",
        orientation === "horizontal"
          ? "right-12 top-1/2 -translate-y-1/2"
          : "bottom-12 left-1/2 -translate-x-1/2 rotate-90",
        className
      )}
      disabled={!canScrollNext}
      onClick={scrollNext}
      {...props}
    >
      <ArrowRightIcon color={"#FFFFFF"} className="h-4 w-4" />
      <span className="sr-only">Next slide</span>
    </CarouselButton>
  )
})

CarouselNext.displayName = "CarouselNext"

export {CarouselNext}
