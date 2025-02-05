import {IFavourite} from "@/shared/types/entities/IFavourite.ts";
import {apiClient, ApiException} from "@/shared/lib";
import {EndpointsType} from "@/shared/types";
import axios from "axios";

export const addToFav = async (tourId: number): Promise<IFavourite> => {
    try {
        const response = await apiClient.post<IFavourite>(`${EndpointsType.FAVOURITES}/${tourId}`)
        return response.data
    } catch (e) {
        if (axios.isAxiosError(e)) {
            throw new ApiException<IFavourite>(e.message, e.response?.status, e.response?.data)
        }
        throw e
    }
}