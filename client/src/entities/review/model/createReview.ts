import {EndpointsType, IReview} from "@/shared/types";
import axios from "axios";
import {apiClient, ApiException} from "@/shared/lib";

export const createReview = async (review: IReview): Promise<IReview> => {
    try {
        const {data} = await apiClient.post<IReview>(EndpointsType.REVIEWS, review)
        return data
    } catch (e) {
        if (axios.isAxiosError(e)) {
            throw new ApiException<IReview>(e.message, e.response?.status, e.response?.data)
        }
        throw e
    }
}