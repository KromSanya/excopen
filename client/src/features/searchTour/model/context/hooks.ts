import {useContext} from "react";
import {SearchContextType} from "./types.ts";
import {SearchContext} from "./context.ts";

export const useSearchContext = (): SearchContextType => {
    const context = useContext(SearchContext)
    if (!context) throw new Error("Context error!")
    return context
}