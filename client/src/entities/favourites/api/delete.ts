import {useMutation} from "@tanstack/react-query";
import {ApiException, deleteEntity} from "@/shared/lib";
import {EndpointsType, ITour} from "@/shared/types";

export const useDeleteFavourite = () => {
    return useMutation<void, ApiException<ITour>, {id: number}>({
        mutationFn: ({id}) => deleteEntity(EndpointsType.FAVOURITES, id),
        onSuccess: () => console.log('Favourite tour deleted successfully'),
        onError: (error) => console.error('Error deleting favourite:', error),
    })
}