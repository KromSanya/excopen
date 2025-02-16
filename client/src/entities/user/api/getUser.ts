import {EndpointsType, IUser} from "@/shared/types";
import {apiClient, ApiException, isAxiosError} from "@/shared/lib";

export const getUser = async (id: number): Promise<IUser> => {
    try {
        const response = await apiClient.get<IUser>(EndpointsType.USERS, {
            params: {id}
        })
        return response.data
    } catch (e) {
        if (isAxiosError(e)) {
            throw new ApiException<IUser>(
                e.message,
                e.response?.status,
                e.response?.data as IUser | undefined
            )
        }
        throw e
    }
}