import {FC, ReactNode, useCallback, useEffect, useMemo, useState} from "react";
import {ITour} from "@/shared/types";
import {TourTrackingContext} from "@/features";

export const TourTrackingProvider: FC<{children: ReactNode}> = ({children}) => {

    const [viewed, setViewed] = useState<ITour[]>([])
    const [favourites, setFavourites] = useState<ITour[]>([])

    useEffect(() => {

        const savedFav = localStorage.getItem('favourites')
        if (savedFav) setFavourites(JSON.parse(savedFav))

        const savedViewed = localStorage.getItem('viewed')
        if (savedViewed) setViewed(JSON.parse(savedViewed))

    }, []);

    const addToFav = useCallback((tour: ITour) => {
        setFavourites(prev => {
            if (!prev.some(favTour => favTour.id === tour.id)) {
                const updated = [...prev, tour]
                localStorage.setItem('favourites', JSON.stringify(updated))
                return updated
            }
            return prev
        })
    }, [])

    const deleteFromFav = useCallback((tour: ITour) => {
        setFavourites(prev => {
            const updated = prev.filter(favTour => favTour.id!== tour.id)
            localStorage.setItem('favourites', JSON.stringify(updated))
            return updated
        })
    }, [])

    const addToViewed = useCallback((tour: ITour) => {
        setViewed(prev => {
            if (!prev.some(viewed => viewed.id === tour.id)) {
                const updated = [...prev, tour]
                localStorage.setItem('viewed', JSON.stringify(updated))
                return updated
            }
            return prev
        })
    }, [])
    
    const context = useMemo(() => ({
        viewed, favourites
    }), [favourites, viewed])
    
    return (
        <TourTrackingContext.Provider value={{context, addToFav, deleteFromFav, addToViewed}}>
            {children}
        </TourTrackingContext.Provider>
    )
}