import {SearchParamsType} from "@/shared/types";

export const serializeSearchParams = (params: SearchParamsType): Record<string, any> => {
    return {
        location: params.location,
        date: params.date ? params.date.toISOString() : undefined,
        accessibility: params.accessibility,
        byCity: params.byCity,
    };
};