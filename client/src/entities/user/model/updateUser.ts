import {EndpointsType, IUser} from "@/shared/types";
import {apiClient, ApiException} from "@/shared/lib";
import axios from "axios";

export const updateUser = async (user: IUser): Promise<IUser> => {
    try {
        const response = await apiClient.put<IUser>(EndpointsType.USERS, {
            params: user
        })
        return response.data
    } catch (e) {
        if (axios.isAxiosError(e)) {
            throw new ApiException<IUser>(e.message, e.response?.status, e.response?.data)
        }
        throw e
    }
}