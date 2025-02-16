import {afterEach, describe, expect} from "vitest";
import {useBreadcrumbs} from "./hooks.ts";
import {MemoryRouter} from "react-router-dom";
import {renderHook} from "@testing-library/react";
import {RouteNames} from "@/shared/types";

describe("breadcrumbs", () => {

    afterEach(() => localStorage.clear())

    it("Возвращаем маршрут для главной страницы", () => {

        const { result } = renderHook(() => useBreadcrumbs(), {
            wrapper: ({ children }) => (
                <MemoryRouter initialEntries={[`/${RouteNames.MAIN}`]}>
                    {children}
                </MemoryRouter>
            ),
        })

        expect(result.current).toEqual([{ path: `/${RouteNames.MAIN}`, label: "Экскурсии" }])

    })

    it("Возвращаем не вложенный маршрут", () => {

        const { result } = renderHook(() => useBreadcrumbs(), {
            wrapper: ({ children }) => (
                <MemoryRouter initialEntries={[`/${RouteNames.PROFILE}`]}>
                    {children}
                </MemoryRouter>
            ),
        })

        expect(result.current).toEqual([
            { path: `/${RouteNames.MAIN}`, label: "Экскурсии" },
            { path: `/${RouteNames.PROFILE}`, label: "Профиль" },
        ])

    })

    it("Возвращаем вложенный маршрут", () => {

        const { result } = renderHook(() => useBreadcrumbs(), {
            wrapper: ({ children }) => (
                <MemoryRouter initialEntries={[`/${RouteNames.TOURS}`]}>
                    {children}
                </MemoryRouter>
            ),
        })

        expect(result.current).toEqual([
            { path: `/${RouteNames.MAIN}`, label: "Экскурсии" },
            { path: `/${RouteNames.TOURS}`, label: "Экскурсии" },
        ])

    })

    it("Возвращаем маршрут с параметром", () => {

        const { result } = renderHook(() => useBreadcrumbs(), {
            wrapper: ({ children }) => (
                <MemoryRouter initialEntries={[`/${RouteNames.TOUR}/example-tour`]}>
                    {children}
                </MemoryRouter>
            ),
        })

        expect(result.current).toEqual([
            { path: `/${RouteNames.MAIN}`, label: "Экскурсии" },
            { path: `/${RouteNames.TOUR}/example-tour`, label: "example-tour" },
        ])
    })

    it("Проверка корректности марщрута при обновлении страницы", () => {

        const { result, rerender } = renderHook(() => useBreadcrumbs(), {
            wrapper: ({ children }) => (
                <MemoryRouter initialEntries={[`/${RouteNames.TOUR}`]}>
                    {children}
                </MemoryRouter>
            ),
        })

        rerender()

        expect(result.current).toEqual([
            { path: `/${RouteNames.MAIN}`, label: "Экскурсии" },
            { path: `/${RouteNames.TOUR}`, label: "Экскурсия" },
        ])

    })

})