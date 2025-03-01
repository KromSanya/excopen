import {EndpointsType, IReview} from "@/shared/types";
import {apiClient, ApiException, isAxiosError} from "@/shared/lib";

export const createReview = async (review: IReview): Promise<void> => {
    try {
        await apiClient.post<IReview>(EndpointsType.REVIEWS, review)
    } catch (e) {
        if (isAxiosError(e)) {
            throw new ApiException<IReview>(e.message, e.response?.status, e.response?.data as IReview | undefined)
        }
        throw e
    }
}