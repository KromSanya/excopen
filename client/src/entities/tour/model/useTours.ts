import {ITour, SearchParamsType} from "@/shared/types";
import {useQuery} from "@tanstack/react-query";
import {ApiException} from "@/shared/lib";
import {getTours} from "@/entities/tour/api";

export const useTours = (params: SearchParamsType | null) => {
    return useQuery<ITour[], ApiException<ITour>>({
        queryKey: ["tours"],
        queryFn: () => getTours(params),
        staleTime: 60_000,
        initialData: []
    })
}