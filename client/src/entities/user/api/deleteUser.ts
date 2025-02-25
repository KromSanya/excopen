import {apiClient, ApiException, isAxiosError} from "@/shared/lib";
import {EndpointsType, IUser} from "@/shared/types";

export const deleteUser = async (id: number): Promise<void> => {
    try {
        await apiClient.delete(EndpointsType.USERS, { params: { id } })
    } catch (e) {
        if (isAxiosError(e)) {
            throw new ApiException<IUser>(e.message, e.response?.status, e.response?.data as IUser | undefined)
        }
        throw e
    }
}