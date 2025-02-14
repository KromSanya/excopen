import {apiClient, ApiException} from "@/shared/lib";
import {EndpointsType, IUser} from "@/shared/types";
import axios from "axios";

export const deleteUser = async (id: number): Promise<void> => {
    try {
        await apiClient.delete(`/${EndpointsType.USERS}/:${id}`)
    } catch (e) {
        if (axios.isAxiosError(e)) {
            throw new ApiException<IUser>(e.message, e.response?.status, e.response?.data)
        }
        throw e
    }
}