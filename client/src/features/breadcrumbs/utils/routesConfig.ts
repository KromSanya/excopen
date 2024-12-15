import {RouteNames} from "@/shared/types";

export const routesConfig = [
    { path: `/${RouteNames.MAIN}`, label: "Главная" },
    { path: `/${RouteNames.ON_BOARDING}`, label: "Онбординг" },
    { path: `/${RouteNames.LOCATIONS}`, label: "Локации" },
    { path: `/${RouteNames.TOURS}`, label: "Экскурсии" },
    { path: `/${RouteNames.TOUR}`, label: "Экскурсия" },
    { path: `/${RouteNames.CONTRIBUTOR}`, label: "Контрибьютор" },
    { path: `/${RouteNames.PROFILE}`, label: "Профиль" },
    { path: `/${RouteNames.FAVOURITES}`, label: "Избранное" },
    { path: `/${RouteNames.CREATE}`, label: "Создать" },
];