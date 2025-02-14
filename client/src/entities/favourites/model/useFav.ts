import {useQuery} from "@tanstack/react-query";
import {IFavourite} from "@/shared/types";
import {ApiException} from "@/shared/lib";
import {getFav} from "@/entities";

export const useFav = (userId: number) => {
    return useQuery<IFavourite[], ApiException<IFavourite>>({
        queryKey: ["fav"],
        queryFn: () => getFav(userId),
        staleTime: 60_000,
        initialData: [],
        enabled: !!userId
    })
}