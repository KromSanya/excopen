import {EndpointsType, ITour} from "@/shared/types";
import {apiClient, ApiException, isAxiosError} from "@/shared/lib";

export const createTour = async (tour: ITour): Promise<void> => {
    try {
        await apiClient.post<ITour>(EndpointsType.TOURS, tour)
    } catch (e) {
        if (isAxiosError(e)) {
            throw new ApiException<ITour>(e.message, e.response?.status, e.response?.data as ITour | undefined)
        }
        throw e
    }
}