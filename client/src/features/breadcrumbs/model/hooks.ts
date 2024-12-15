import {useLocation} from "react-router-dom";
import {useEffect, useState} from "react";
import {RouteNames} from "@/shared/types";
import {BreadcrumbType} from "./types.ts";
import {routesConfig} from "@/features/breadcrumbs/utils";

export const useBreadcrumbs = (): BreadcrumbType[] => {

    const location = useLocation();
    const [breadcrumbs, setBreadcrumbs] = useState<BreadcrumbType[]>([{ path: `/${RouteNames.MAIN}`, label: 'Главная' }]);

    useEffect(() => {

        const path: string[] = location.pathname.split('/').filter(Boolean);
        const currentPath: string = `/${path.join('/')}`;

        const currentRoute = routesConfig.find(route => route.path === currentPath)
        const currentLabel = currentRoute?.label ?? path[path.length - 1] ?? RouteNames.MAIN

        if (!breadcrumbs.find(breadcrumb => breadcrumb.path === currentPath)) {
            setBreadcrumbs(
                prev => [
                    ...prev,
                    {path: currentPath, label: decodeURIComponent(currentLabel)}
                ]
            );
        }

    }, [location, breadcrumbs]);

    return breadcrumbs;

}