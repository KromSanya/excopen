import {FC, ReactNode} from "react";
import {BrowserRouter} from "react-router-dom";
import {AuthProvider} from "./AuthProvider.tsx";
import {TourTrackingProvider} from "./TourTrackingProvider.tsx";
import {QueryClient, QueryClientProvider} from "@tanstack/react-query";
import {SearchProvider} from "@/features";

const queryClient = new QueryClient()

export const AppProvider:FC<{children: ReactNode}> = ({children}) => {
    return(
        <QueryClientProvider client={queryClient}>
            <AuthProvider>
                <SearchProvider>
                    <TourTrackingProvider>
                        <BrowserRouter>
                            {children}
                        </BrowserRouter>
                    </TourTrackingProvider>
                </SearchProvider>
            </AuthProvider>
        </QueryClientProvider>
    )
}