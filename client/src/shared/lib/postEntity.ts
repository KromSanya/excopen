import axios from "axios";
import {apiClient, ApiException} from "@/shared/lib";
import {EndpointsType} from "@/shared/types";

export const postEntity = async <T>(endpoint: EndpointsType, id: number): Promise<T> => {
    try {
        const response = await apiClient.post<T>(`/${endpoint}/:${id}`)
        return response.data
    } catch (e) {
        if (axios.isAxiosError(e)) {
            throw new ApiException<T>(
                e.message,
                e.response?.status,
                e.response?.data
            )
        }
        throw e
    }
}