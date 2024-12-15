import * as React from "react";
import {cn} from "@/app/lib/utils.ts";
import {useCarousel} from "@/shared/ui/carousel/hooks.ts";

const CarouselContent = React.forwardRef<
    HTMLDivElement,
    React.HTMLAttributes<HTMLDivElement>
>(({ className, ...props }, ref) => {

    const { carouselRef } = useCarousel()

    return (
        <div ref={carouselRef} className="overflow-hidden">
            <div
                ref={ref}
                className={cn(
                    "flex",
                    className
                )}
                {...props}
            />
        </div>
    )
})

CarouselContent.displayName = "CarouselContent"

export {CarouselContent}