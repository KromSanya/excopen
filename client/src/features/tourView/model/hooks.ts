import {useContext} from "react";
import {TourViewContext} from "@/features/tourView/model/context.ts";

export const useTourViewContext = () => {
    const context = useContext(TourViewContext)
    if (!context) throw new Error("Context error!")
    return context
}