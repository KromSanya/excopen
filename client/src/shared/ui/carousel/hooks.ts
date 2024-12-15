import * as React from "react";
import {CarouselContext} from "@/shared/ui/carousel/context.ts";

export function useCarousel() {
    const context = React.useContext(CarouselContext)
    if (!context) throw new Error("useCarousel must be used within a <Carusel />")
    return context
}