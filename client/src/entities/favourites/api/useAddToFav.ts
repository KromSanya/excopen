import {useMutation, useQueryClient} from "@tanstack/react-query";
import {IFavourite} from "@/shared/types";
import {ApiException} from "@/shared/lib";
import {addToFav} from "@/entities/favourites/model";

export const useAddToFav = () => {

    const queryClient = useQueryClient()

    return useMutation<IFavourite, ApiException<IFavourite>, number>({
        mutationFn: addToFav,
        onSuccess: () => queryClient.invalidateQueries({queryKey: ["addToFav"]}),
        onError: (e: ApiException<IFavourite>) => {
            throw new ApiException<IFavourite>(e.message, e.statusCode, e.data)
        }
    })

}