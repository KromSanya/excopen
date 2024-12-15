import {useMutation} from "@tanstack/react-query";
import {EndpointsType, ITour} from "@/shared/types";
import {ApiException, postEntity} from "@/shared/lib";

export const usePostFavourite = () => {
    return useMutation<ITour, ApiException<ITour>, {id: number}>({
        mutationFn: ({id}) => postEntity(EndpointsType.FAVOURITES, id),
        onSuccess: data => console.log(data),
        onError: error => console.error("API Error:", error.message, error.statusCode, error.data)
    })
}