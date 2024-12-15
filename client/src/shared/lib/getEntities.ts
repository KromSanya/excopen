import {apiClient, ApiException} from "@/shared/lib";
import axios from "axios";
import {EndpointsType} from "@/shared/types/api/EndpointsType.ts";

export const getEntities = async <T>(endpoint: EndpointsType): Promise<T[]> => {
    try {
        const response = await apiClient.get<T[]>(`/${endpoint}`);
        return response.data;
    } catch (e) {
        if (axios.isAxiosError(e)) {
            throw new ApiException<T>(
                e.message,
                e.response?.status,
                e.response?.data
            );
        }
        throw e
    }
}