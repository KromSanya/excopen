import {apiClient, ApiException} from "@/shared/lib";
import {EndpointsType, IFavourite} from "@/shared/types";
import axios from "axios";

export const getFav = async (userId: number): Promise<IFavourite[]> => {
    try {
        const response = await apiClient.get<IFavourite[]>(`${EndpointsType.FAVOURITES}/${userId}`)
        return response.data
    } catch (e) {
        if (axios.isAxiosError(e)) {
            throw new ApiException<IFavourite>(e.message, e.response?.status, e.response?.data)
        }
        throw e
    }
}