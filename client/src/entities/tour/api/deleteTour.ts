import {useMutation} from "@tanstack/react-query";
import {ApiException, deleteEntity} from "@/shared/lib";
import {EndpointsType, ITour} from "@/shared/types";

export const useDeleteTour = () => {
    return useMutation<void, ApiException<ITour>, {id: number}>({
        mutationFn: ({id}) => deleteEntity(EndpointsType.TOURS, id),
        onSuccess: () => console.log('Tour deleted successfully'),
        onError: (error) => console.error('Error deleting tour:', error)
    })
}