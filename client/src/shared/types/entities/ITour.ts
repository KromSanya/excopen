import {IDescription} from "@/shared/types/entities/IDescription.ts";
import {ILocation} from "./ILocation.ts"
import {TourAccessibility} from "@/shared/types/entities/TourAccessibility.ts";
import {IContributor} from "@/shared/types/entities/IContributor.ts";
import {IContacts} from "@/shared/types/entities/IContacts.ts";
import {IReview} from "@/shared/types/entities/IReview.ts";

export interface ITour {
    id: number
    title: string
    shortDescription: string
    images: string[]
    map?: string
    description?: IDescription
    location?: ILocation
    contributor: IContributor
    price: number
    priceForPerson: number
    groupCapacity?: number
    formatBehavior?: string
    format?: string
    duration: string
    routeLength: number
    minAge?: number
    rating: number
    ratingCount: number
    date?: Date
    accessibility?: TourAccessibility
    byCity?: boolean
    contact?: IContacts
    reviews: IReview[]
}