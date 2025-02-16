import {RouteNames} from "@/shared/types";
import {BreadcrumbType} from "@/features/breadcrumbs/model/types.ts";

export const routesConfig = new Map<string, BreadcrumbType>([
    [RouteNames.MAIN, { path: `/${RouteNames.MAIN}`, label: "Экскурсии" }],
    [RouteNames.ON_BOARDING, { path: `/${RouteNames.ON_BOARDING}`, label: "Онбординг" }],
    [RouteNames.LOCATIONS, { path: `/${RouteNames.LOCATIONS}`, label: "Локации" }],
    [RouteNames.TOURS, { path: `/${RouteNames.TOURS}`, label: "Экскурсии" }],
    [RouteNames.TOUR, { path: `/${RouteNames.TOUR}`, label: "Экскурсия" }],
    [RouteNames.CONTRIBUTOR, { path: `/${RouteNames.CONTRIBUTOR}`, label: "Контрибьютор" }],
    [RouteNames.PROFILE, { path: `/${RouteNames.PROFILE}`, label: "Профиль" }],
    [RouteNames.FAVOURITES, { path: `/${RouteNames.FAVOURITES}`, label: "Избранное" }],
    [RouteNames.CREATE, { path: `/${RouteNames.CREATE}`, label: "Создать" }]
]);
