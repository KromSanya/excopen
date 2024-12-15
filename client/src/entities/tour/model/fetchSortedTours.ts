import {apiClient, ApiException} from "@/shared/lib";
import axios from "axios";
import {ITour} from "@/shared/types";
import {SortValues} from "@/shared/types/features";

export const fetchSortedTours = async (params: SortValues): Promise<ITour[]> => {
    try {
        const response = await apiClient.get<ITour[]>(`/tours`, {params: params});
        return response.data;
    } catch (e) {
        if (axios.isAxiosError(e)) {
            throw new ApiException<ITour>(
                e.message,
                e.response?.status,
                e.response?.data
            );
        }
        throw e
    }
}