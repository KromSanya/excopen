import {useMutation, useQueryClient} from "@tanstack/react-query";
import {ApiException} from "@/shared/lib";
import {createTour} from "@/entities/tour/model";
import {ITour} from "@/shared/types";

export const useCreateTour = () => {

    const queryClient = useQueryClient()

    return useMutation<ITour, ApiException<ITour>, ITour>({
        mutationFn: createTour,
        onSuccess: () => queryClient.invalidateQueries({queryKey: ["createTour"]}),
        onError: (e: ApiException<ITour>) => {
            throw new ApiException<ITour>(e.message, e.statusCode, e.data)
        }
    })

}