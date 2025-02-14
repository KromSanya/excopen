import {apiClient, ApiException} from "@/shared/lib";
import axios from "axios";
import {EndpointsType, ITour} from "@/shared/types";

export const deleteTour = async (id: number): Promise<void> => {
    try {
        await apiClient.delete(`/${EndpointsType.TOURS}/:${id}`)
    } catch (e) {
        if (axios.isAxiosError(e)) {
            throw new ApiException<ITour>(e.message, e.response?.status, e.response?.data)
        }
        throw e
    }
}