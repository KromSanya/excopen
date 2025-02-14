import {useQuery} from "@tanstack/react-query";
import {IReview} from "@/shared/types";
import {ApiException} from "@/shared/lib";
import {getReview} from "@/entities/review/api";

export const useReview = (id: number) => {
    return useQuery<IReview, ApiException<IReview>>({
        queryKey: ["review"],
        queryFn: () => getReview(id),
        staleTime: 60_000,
        enabled: !!id
    })
}