import {useMutation, useQueryClient} from "@tanstack/react-query";
import {ITour} from "@/shared/types";
import {ApiException} from "@/shared/lib";
import {updateTour} from "@/entities/tour/model";

export const useUpdateTour = () => {

    const queryClient = useQueryClient()

    return useMutation<ITour, ApiException<ITour>, ITour>({
        mutationFn: updateTour,
        onSuccess: () => queryClient.invalidateQueries({queryKey: ["updateTour"]}),
        onError: (e: ApiException<ITour>) => {
            throw new ApiException<ITour>(e.message, e.statusCode, e.data)
        }
    })

}