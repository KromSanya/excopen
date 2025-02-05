import {apiClient, ApiException} from "@/shared/lib";
import {EndpointsType, IFavourite} from "@/shared/types";
import axios from "axios";

export const deleteFromFav = async (tourId: number): Promise<void> => {
    try {
        await apiClient.delete(`/${EndpointsType.FAVOURITES}/:${tourId}`)
    } catch (e) {
        if (axios.isAxiosError(e)) {
            throw new ApiException<IFavourite>(e.message, e.response?.status, e.response?.data)
        }
        throw e
    }
}