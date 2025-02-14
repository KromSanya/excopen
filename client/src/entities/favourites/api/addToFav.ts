import {IFavourite} from "@/shared/types/entities/IFavourite.ts";
import {apiClient, ApiException, isAxiosError} from "@/shared/lib";
import {EndpointsType} from "@/shared/types";

export const addToFav = async (tourId: number): Promise<void> => {
    try {
        await apiClient.post<IFavourite>(`${EndpointsType.FAVOURITES}/${tourId}`)
    } catch (e) {
        if (isAxiosError(e)) {
            throw new ApiException<IFavourite>(
                e.message,
                e.response?.status,
                e.response?.data as IFavourite | undefined
            )
        }
        throw e
    }
}