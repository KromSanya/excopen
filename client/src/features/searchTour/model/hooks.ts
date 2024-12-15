import {useContext} from "react";
import {SearchTourContext} from "./context.ts";
import {SearchTourContextType} from "./types.ts";

export const useSearchTourContext = (): SearchTourContextType => {
    const context = useContext(SearchTourContext)
    if (!context) throw new Error("Context error!")
    return context
}