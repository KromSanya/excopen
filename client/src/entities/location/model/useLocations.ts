import {useQuery} from "@tanstack/react-query";
import {ApiException} from "@/shared/lib";
import {ILocation} from "@/shared/types";
import {getLocations} from "@/entities";

export const useLocations = () => {
    return useQuery<ILocation[], ApiException<ILocation>>({
        queryKey: ["locations"],
        queryFn: () => getLocations(),
        staleTime: 60_000,
        initialData: []
    })
}