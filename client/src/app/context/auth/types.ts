import {UserRole} from "@/shared/types";

export type AuthContextType = {
    isAuth: boolean
    role: UserRole | null
}