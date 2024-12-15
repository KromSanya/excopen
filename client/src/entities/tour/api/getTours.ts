import {useQuery} from "@tanstack/react-query";
import {ApiException, getEntities} from "@/shared/lib";
import {EndpointsType, ITour} from "@/shared/types";

export const useGetTours = () => {

    const {data, isLoading, error, isError} = useQuery<ITour[], ApiException<ITour>>({
        queryKey: ['tours'],
        queryFn: () => getEntities<ITour>(EndpointsType.TOURS),
        staleTime: 60000
    })

    if (isLoading) return {isLoading: true, data: [], error: null}
    if (isError) return {isLoading: false, data: [], error: error}

    return {data, isLoading, error: null}

}