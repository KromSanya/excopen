import {useContext} from "react";
import {AuthContextType} from "./types.ts";
import {AuthContext} from "@/app/context";

export const useAuthContext = (): AuthContextType => {
    const context = useContext(AuthContext);
    if (!context) throw new Error("Context error!");
    return context;
};