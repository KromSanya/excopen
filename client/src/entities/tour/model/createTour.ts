import {EndpointsType, ITour} from "@/shared/types";
import {apiClient, ApiException} from "@/shared/lib";
import axios from "axios";

export const createTour = async (tour: ITour): Promise<ITour> => {
    try {
        const response = await apiClient.post<ITour>(EndpointsType.TOURS, {
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