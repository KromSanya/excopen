import {SearchParamsType} from "@/shared/types";

export const serializeSearchParams = (params: SearchParamsType): {
    date: string | undefined;
    accessibility: string;
    byCity: boolean;
    location: string
} => {
    return {
        location: params.location,
        date: params.date ? params.date.toISOString() : undefined,
        accessibility: params.accessibility,
        byCity: params.byCity,
    };
};