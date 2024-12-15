import {FC, ReactNode, useState} from "react";
import {ITour} from "@/shared/types";
import {TourViewContext} from "@/features"

export const TourViewProvider: FC<{children: ReactNode}> = ({children}) => {

    const [viewedTours, setViewedTours] = useState<ITour[]>([])

    return (
        <TourViewContext.Provider value={{viewedTours, setViewedTours}}>
            {children}
        </TourViewContext.Provider>
    );
};