import {apiClient, ApiException, isAxiosError} from "@/shared/lib";
import {EndpointsType, IFavourite} from "@/shared/types";

export const getFav = async (userId: number): Promise<IFavourite[]> => {
    try {
        const response = await apiClient.get<IFavourite[]>(EndpointsType.FAVOURITES, {
            params: {userId}
        })
        return response.data
    } catch (e) {
        if (isAxiosError(e)) {
            throw new ApiException<IFavourite>(
                e.message,
                e.response?.status,
                e.response?.data as IFavourite[] | undefined
            )
        }
        throw e
    }
}