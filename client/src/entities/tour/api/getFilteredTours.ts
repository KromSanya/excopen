import {useQuery} from "@tanstack/react-query";
import {ITour, SearchParamsType} from "@/shared/types";
import {ApiException} from "@/shared/lib";
import {fetchFilteredTours} from "@/entities/tour/model";

export const useGetFilteredTours = (params: SearchParamsType) => {

    const {data, isLoading, isError, error} = useQuery<ITour[], ApiException<ITour>>({
        queryKey: ['filteredTours'],
        queryFn: () => fetchFilteredTours(params),
        staleTime: 60000
    })

    if (isLoading) return {isLoading: true, data: [], error: null}
    if (isError) return {isLoading: false, data: [], error: error}

    return {data, isLoading, error: null}

}