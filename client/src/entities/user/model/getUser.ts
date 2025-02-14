import {EndpointsType, IUser} from "@/shared/types";
import {apiClient, ApiException} from "@/shared/lib";
import axios from "axios";

export const getUser = async (id: number): Promise<IUser> => {
    try {
        const response = await apiClient.get<IUser>(`${EndpointsType.USERS}/${id}`)
        return response.data
    } catch (e) {
        if (axios.isAxiosError(e)) {
            throw new ApiException<IUser>(e.message, e.response?.status, e.response?.data)
        }
        throw e
    }
}