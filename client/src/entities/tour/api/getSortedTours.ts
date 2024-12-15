import {useQuery} from "@tanstack/react-query";
import {ITour} from "@/shared/types";
import {ApiException} from "@/shared/lib";
import {fetchSortedTours} from "@/entities/tour/model";
import {SortValues} from "@/shared/types/features";

export const useGetSortedTours = (params: SortValues) => {

    const {data, isLoading, isError, error} = useQuery<ITour[], ApiException<ITour>>({
        queryKey: ['sortedTours'],
        queryFn: () => fetchSortedTours(params),
        staleTime: 60000
    })

    if (isLoading) return {isLoading: true, data: [], error: null}
    if (isError) return {isLoading: false, data: [], error: error}

    return {data, isLoading, error: null}

}