import {useMutation, useQueryClient} from "@tanstack/react-query";
import {ApiException} from "@/shared/lib";
import {IUser} from "@/shared/types";
import {deleteUser} from "@/entities/user/model";

export const useDeleteUser = () => {

    const queryClient = useQueryClient()

    return useMutation<void, ApiException<IUser>, number>({
        mutationFn: deleteUser,
        onSuccess: () => queryClient.invalidateQueries({queryKey: ["deleteUser"]}),
        onError: (e: ApiException<IUser>) => {
            throw new ApiException<IUser>(e.message, e.statusCode, e.data)
        }
    })

}