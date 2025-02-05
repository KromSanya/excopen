import {useMutation, useQueryClient} from "@tanstack/react-query";
import {IUser} from "@/shared/types";
import {ApiException} from "@/shared/lib";
import {updateUser} from "@/entities/user/model";

export const useUpdateUser = () => {

    const queryClient = useQueryClient()

    return useMutation<IUser, ApiException<IUser>, IUser>({
        mutationFn: updateUser,
        onSuccess: () => queryClient.invalidateQueries({queryKey: ["updateUser"]}),
        onError: (e: ApiException<IUser>) => {
            throw new ApiException<IUser>(e.message, e.statusCode, e.data)
        }
    })

}