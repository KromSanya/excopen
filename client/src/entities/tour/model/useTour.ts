import {useQuery} from "@tanstack/react-query";
import {ApiException} from "@/shared/lib";
import {ITour} from "@/shared/types";
import {getTourById} from "@/entities/tour/api";

export const useTour = (id: number) => {
    return useQuery<ITour, ApiException<ITour>>({
        queryKey: ["tour"],
        queryFn: () => getTourById(id),
        staleTime: 60_000,
        enabled: !!id
    })
}