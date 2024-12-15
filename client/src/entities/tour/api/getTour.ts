import {useQuery} from "@tanstack/react-query";
import {ApiException, getEntity} from "@/shared/lib";
import {EndpointsType, ITour} from "@/shared/types";

export const useGetTour = (id: number) => {

    const {data, isLoading, error, isError} = useQuery<ITour, ApiException<ITour>>({
        queryKey: ['tour', id],
        queryFn: () => getEntity<ITour>(EndpointsType.TOURS, id),
        staleTime: 60000,
        enabled: !!id
    })

    if (isLoading) return {isLoading: true, data: [], error: null}
    if (isError) return {isLoading: false, data: [], error: error}

    return {data, isLoading, error: null}

}