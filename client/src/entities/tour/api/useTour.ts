import {useQuery} from "@tanstack/react-query";
import {ApiException} from "@/shared/lib";
import {ITour} from "@/shared/types";
import {getTourById} from "@/entities/tour/model";

export const useTour = (id: number) => {
    return useQuery<ITour, ApiException<ITour>>({
        queryKey: ["tour", id],
        queryFn: () => getTourById(id),
        staleTime: 60_000,
        enabled: !!id
    })
}