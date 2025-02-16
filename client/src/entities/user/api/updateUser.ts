import {EndpointsType, IUser} from "@/shared/types";
import {apiClient, ApiException, isAxiosError} from "@/shared/lib";

export const updateUser = async (user: IUser): Promise<IUser> => {
    try {
        const response = await apiClient.put<IUser>(EndpointsType.USERS, {
            params: user
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