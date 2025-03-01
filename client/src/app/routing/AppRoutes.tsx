import {Navigate, Route, Routes} from "react-router-dom";
import {RouteNames, UserRole} from "@/shared/types";
import {RequireAuth} from "./RequireAuth.tsx";
import {
    ContributorInfoPage, CreateTourPage, FavouritesPage,
    HomePage,
    Layout,
    LocationsPage,
    OnBoardingPage,
    ProfilePage,
    TourPage,
    ToursPage
} from "@/pages";
import {FC} from "react";

export const AppRoutes: FC = () => {
    return (
        <Routes>
            <Route
                path={`/${RouteNames.ON_BOARDING}`}
                element={
                    <RequireAuth role={UserRole.client}>
                        <OnBoardingPage/>
                    </RequireAuth>
                }
            />
            <Route path={"/"} element={<Layout/>}>
                <Route path={RouteNames.MAIN} element={<HomePage/>}/>
                <Route path={RouteNames.LOCATIONS} element={<LocationsPage/>}/>
                <Route path={`${RouteNames.TOURS}/:location`} element={<ToursPage/>}/>
                <Route path={`${RouteNames.TOUR}/:title`} element={<TourPage/>}/>
                <Route path={`${RouteNames.CONTRIBUTOR}/:name`} element={<ContributorInfoPage/>}/>
                <Route path={RouteNames.PROFILE} element={<ProfilePage/>}/>
                <Route path={RouteNames.FAVOURITES} element={<FavouritesPage/>}/>
                <Route
                    path={RouteNames.CREATE}
                    element={
                        <RequireAuth role={UserRole.contributor}>
                            <CreateTourPage/>
                        </RequireAuth>
                    }
                />
                <Route path="*" element={<Navigate to={RouteNames.MAIN} replace />} />
            </Route>
        </Routes>
    );
};