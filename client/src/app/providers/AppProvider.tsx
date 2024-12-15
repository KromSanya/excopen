import {FC, ReactNode} from "react";
import {BrowserRouter} from "react-router-dom";
import {AuthProvider} from "./AuthProvider.tsx";
import {SearchTourProvider} from "./SearchTourProvider.tsx";
import {TourViewProvider} from "./TourViewProvider.tsx";

export const AppProvider:FC<{children: ReactNode}> = ({children}) => {
    return(
        <AuthProvider>
            <SearchTourProvider>
                <TourViewProvider>
                    <BrowserRouter>
                        {children}
                    </BrowserRouter>
                </TourViewProvider>
            </SearchTourProvider>
        </AuthProvider>
    )
}