import {apiClient, ApiException, isAxiosError} from "@/shared/lib";
import {EndpointsType, ITour} from "@/shared/types";

export const deleteTour = async (id: number): Promise<void> => {
    try {
        await apiClient.delete(EndpointsType.TOURS, { params: { id } })
    } catch (e) {
        if (isAxiosError(e)) {
            throw new ApiException<ITour>(e.message, e.response?.status, e.response?.data as ITour | undefined)
        }
        throw e
    }
}