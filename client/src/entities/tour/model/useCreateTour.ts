import {useMutation, useQueryClient} from "@tanstack/react-query";
import {ApiException} from "@/shared/lib";
import {ITour} from "@/shared/types";
import {createTour} from "@/entities/tour/api";

export const useCreateTour = () => {

    const queryClient = useQueryClient()

    return useMutation<void, ApiException<ITour>, ITour>({
        mutationFn: createTour,
        onSuccess: () => {
            queryClient.invalidateQueries({queryKey: ["tour"]})
            queryClient.invalidateQueries({queryKey: ["tours"]})
        },
        onError: (e: ApiException<ITour>) => {
            throw new ApiException<ITour>(e.message, e.statusCode, e.data)
        }
    })

}