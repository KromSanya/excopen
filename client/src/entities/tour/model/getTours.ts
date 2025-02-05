import {EndpointsType, ITour, SearchParamsType} from "@/shared/types";
import {apiClient, ApiException} from "@/shared/lib";
import axios from "axios";

export const getTours = async (params: SearchParamsType | null): Promise<ITour[]> => {
    try {
        const response = await apiClient.get<ITour[]>(EndpointsType.TOURS, {
            params: params
        })
        return response.data
    } catch (e) {
        if (axios.isAxiosError(e)) {
            throw new ApiException<ITour>(e.message, e.response?.status, e.response?.data)
        }
        throw e
    }
}