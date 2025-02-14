import {useQuery} from "@tanstack/react-query";
import {IUser} from "@/shared/types";
import {ApiException} from "@/shared/lib";
import {getUser} from "@/entities";

export const useUser = (id: number) => {
    return useQuery<IUser, ApiException<IUser>>({
        queryKey: ["user"],
        queryFn: () => getUser(id),
        staleTime: 60_000,
        enabled: !!id
    })
}