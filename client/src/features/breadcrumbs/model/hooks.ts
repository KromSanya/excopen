import {useLocation} from "react-router-dom";
import {useEffect, useMemo, useState} from "react";
import { RouteNames } from "@/shared/types";
import { BreadcrumbType } from "./types.ts";
import {routesConfig} from "@/features/breadcrumbs/config";

export const useBreadcrumbs = (): BreadcrumbType[] => {

    const initialState: BreadcrumbType[] = useMemo(() => {
        return [routesConfig.get(RouteNames.MAIN)!]
    }, [])

    const location = useLocation()
    const [breadcrumbs, setBreadcrumbs] = useState<
        BreadcrumbType[]
    >(() => {
        const savedBreadcrumbs = localStorage.getItem('breadcrumbs');
        return savedBreadcrumbs ? JSON.parse(savedBreadcrumbs) : initialState;
    })

    useEffect(() => {

        const path: string[] = location.pathname.split('/').filter(Boolean);
        
        const currentPath: string = `/${path.join('/')}`
        const currentRoute: BreadcrumbType = routesConfig.get(path[path.length - 1])!

        const currentLabel: string = currentRoute?.label ?? path[path.length - 1] ?? RouteNames.MAIN

        setBreadcrumbs(prev => {

            const filteredBreadcrumbs = prev.filter(
                crumb => crumb.path !== currentPath
            )

            if (currentPath === `/${RouteNames.MAIN}`) return initialState

            const updatedBreadcrumbs = [
                ...filteredBreadcrumbs,
                { path: currentPath, label: decodeURIComponent(currentLabel) }
            ]

            localStorage.setItem('breadcrumbs', JSON.stringify(updatedBreadcrumbs))

            return updatedBreadcrumbs

        })

    }, [initialState, location])

    return breadcrumbs;
    
}