import {useMutation, useQueryClient} from "@tanstack/react-query";
import {IReview} from "@/shared/types";
import {ApiException} from "@/shared/lib";
import {updateReview} from "@/entities/review/api";

export const useUpdateReview = () => {

    const queryClient = useQueryClient()

    return useMutation<IReview, ApiException<IReview>,IReview>({
        mutationFn: updateReview,
        onSuccess: () => {
            queryClient.invalidateQueries({queryKey: ["reviews", "tour"]})
            queryClient.invalidateQueries({queryKey: ["reviews", "user"]})
        },
        onError: (e: ApiException<IReview>) => {
            throw new ApiException<IReview>(e.message, e.statusCode, e.data)
        }
    })

}