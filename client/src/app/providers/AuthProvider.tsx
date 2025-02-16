import {UserRole} from "@/shared/types";
import {ReactNode} from "react";
import {AuthContext} from "@/app/context";

export const AuthProvider = ({ children }: { children: ReactNode }) => {
    return (
        <AuthContext.Provider value={{
            role: UserRole.client,
            isAuth: true
        }}>
            {children}
        </AuthContext.Provider>
    )
};