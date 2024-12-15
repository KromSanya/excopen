import {useQuery} from "@tanstack/react-query";
import {EndpointsType, ITour} from "@/shared/types";
import {ApiException, getEntities} from "@/shared/lib";

export const useGetFavourites = (): ITour[] => {

    const {data = []} = useQuery<ITour[], ApiException<ITour>>({
        queryKey: ["favourites"],
        queryFn: () => getEntities<ITour>(EndpointsType.FAVOURITES),
        staleTime: 60000
    })

    return data

}