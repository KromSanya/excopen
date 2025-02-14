import {useMutation, useQueryClient} from "@tanstack/react-query";
import {ITour} from "@/shared/types";
import {ApiException} from "@/shared/lib";
import {deleteTour} from "@/entities/tour/model";

export const useDeleteTour = () => {

    const queryClient = useQueryClient()

    return useMutation<void, ApiException<ITour>, number>({
        mutationFn: deleteTour,
        onSuccess: () => queryClient.invalidateQueries({queryKey: ["deleteTour"]}),
        onError: (e: ApiException<ITour>) => {
            throw new ApiException<ITour>(e.message, e.statusCode, e.data)
        }
    })

}