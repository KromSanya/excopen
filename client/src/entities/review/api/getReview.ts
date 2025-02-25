import {apiClient, ApiException, isAxiosError} from "@/shared/lib";
import {EndpointsType, IReview} from "@/shared/types";

export const getReview = async (id: number): Promise<IReview> => {
    try {
        const response = await apiClient.get<IReview>(EndpointsType.REVIEWS, {
            params: { id }
        })
        return response.data
    } catch (e) {
        if (isAxiosError(e)) {
            throw new ApiException<IReview>(e.message, e.response?.status, e.response?.data as IReview | undefined)
        }
        throw e
    }
}