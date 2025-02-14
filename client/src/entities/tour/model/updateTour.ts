import {apiClient, ApiException} from "@/shared/lib";
import {EndpointsType, ITour} from "@/shared/types";
import axios from "axios";

export const updateTour = async (tour: ITour): Promise<ITour> => {
    try {
        const response = await apiClient.put<ITour>(EndpointsType.TOURS, {
            params: tour
        })
        return response.data
    } catch (e) {
        if (axios.isAxiosError(e)) {
            throw new ApiException<ITour>(e.message, e.response?.status, e.response?.data)
        }
        throw e
    }
}