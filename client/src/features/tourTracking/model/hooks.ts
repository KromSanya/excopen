import {useContext} from "react";
import {TourTrackingContext} from "./context.ts";

export const useTourTrackingContext = () => {
    const context = useContext(TourTrackingContext)
    if (!context) throw new Error("Context error!")
    return context
}