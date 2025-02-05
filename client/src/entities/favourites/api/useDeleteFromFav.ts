import {useMutation, useQueryClient} from "@tanstack/react-query";
import {ApiException} from "@/shared/lib";
import {IFavourite} from "@/shared/types";
import {deleteFromFav} from "@/entities/favourites/model";

export const useDeleteFromFav = () => {

    const queryClient = useQueryClient()

    return useMutation<void, ApiException<IFavourite>, number>({
        mutationFn: deleteFromFav,
        onSuccess: () => queryClient.invalidateQueries({queryKey: ["deleteFromFav"]}),
        onError: (e: ApiException<IFavourite>) => {
            throw new ApiException<IFavourite>(e.message, e.statusCode, e.data)
        }
    })

}