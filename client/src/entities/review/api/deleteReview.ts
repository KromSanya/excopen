import {apiClient, ApiException, isAxiosError} from "@/shared/lib";
import {EndpointsType, IReview} from "@/shared/types";

export const deleteReview = async (id: number): Promise<void> => {
    try {
        await apiClient.delete<IReview>(EndpointsType.REVIEWS, { params: { id } })
    } catch (e) {
        if (isAxiosError(e)) {
            throw new ApiException<IReview>(e.message, e.response?.status, e.response?.data as IReview | undefined)
        }
        throw e
    }
}