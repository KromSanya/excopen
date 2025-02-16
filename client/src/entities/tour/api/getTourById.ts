import {EndpointsType, ITour} from "@/shared/types";
import {apiClient, ApiException, isAxiosError} from "@/shared/lib";

export const getTourById = async (id: number): Promise<ITour> => {
    try {
        const response = await apiClient.get<ITour>(EndpointsType.TOURS, {
            params: {id}
        })
        return response.data
    } catch (e) {
        if (isAxiosError(e)) {
            throw new ApiException<ITour>(
                e.message,
                e.response?.status,
                e.response?.data as ITour | undefined
            )
        }
        throw e
    }
}