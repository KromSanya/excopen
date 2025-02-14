import {EndpointsType, ILocation} from "@/shared/types";
import {apiClient, ApiException} from "@/shared/lib";
import {isAxiosError} from "@/shared/lib/isAxiosError.ts";

export const getLocations = async (): Promise<ILocation[]> => {
    try {
        const response = await apiClient.get<ILocation[]>(EndpointsType.LOCATIONS)
        return response.data
    } catch (e) {
        if (isAxiosError(e)) {
            throw new ApiException<ILocation>(
                e.message,
                e.response?.status,
                e.response?.data as ILocation[] | undefined
            )
        }
        throw e
    }
}