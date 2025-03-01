import {apiClient, ApiException, isAxiosError} from "@/shared/lib";
import {EndpointsType, IFavourite} from "@/shared/types";

export const deleteFromFav = async (tourId: number): Promise<void> => {
    try {
        await apiClient.delete(EndpointsType.FAVOURITES, { params: {tourId} })
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