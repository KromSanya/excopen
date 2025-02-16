import React, {FC} from "react";
import {UserRole} from "@/shared/types";
import {Navigate} from "react-router-dom";
import {useAuthContext} from "@/app/context";

type RequireAuthProps = {
    children: React.ReactNode;
    role?: UserRole
}

export const RequireAuth: FC<RequireAuthProps> = (props) => {

    const {isAuth, role} = useAuthContext();

    if (!isAuth || (props.role !== null && props.role !== role)) {
        return <Navigate to={"/main"}/>
    }

    return props.children;

};