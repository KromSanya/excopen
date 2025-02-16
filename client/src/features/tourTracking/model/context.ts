import {createContext} from "react";
import {TourTrackingContextType} from "@/features/tourTracking/model/types.ts";

export const TourTrackingContext = createContext<TourTrackingContextType>(null!)