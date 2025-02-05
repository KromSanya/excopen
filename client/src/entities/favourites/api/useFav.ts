import {useQuery} from "@tanstack/react-query";
import {IFavourite} from "@/shared/types";
import {ApiException} from "@/shared/lib";
import {getFav} from "@/entities/favourites/model";

export const useFav = (userId: number) => {
    return useQuery<IFavourite[], ApiException<IFavourite>>({
        queryKey: ["fav", userId],
        queryFn: () => getFav(userId),
        staleTime: 60_000,
        initialData: []
    })
}