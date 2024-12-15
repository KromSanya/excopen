import {apiClient} from "@/shared/lib/apiClient.ts";
import axios from "axios";
import {ApiException} from "@/shared/lib/exceptions.ts";
import {EndpointsType} from "@/shared/types/api/EndpointsType.ts";

export const deleteEntity = async <T>(endpoint: EndpointsType, id: number): Promise<void> => {
    try {
        await apiClient.delete(`/${endpoint}/:${id}`)
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