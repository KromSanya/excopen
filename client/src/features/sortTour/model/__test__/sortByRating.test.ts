import {describe, it, expect} from "vitest";
import {sortByRating} from "@/features";
import {ITour} from "@/shared/types";

const mockArray: ITour[] = [
    {
        id: 1,
        title: 'Tour 1',
        shortDescription: '',
        images: [],
        price: 0,
        priceForPerson: 0,
        duration: '',
        routeLength: 0,
        rating: 4.5,
        ratingCount: 0,
    },
    {
        id: 2,
        title: 'Tour 2',
        shortDescription: '',
        images: [],
        price: 0,
        priceForPerson: 0,
        duration: '',
        routeLength: 0,
        rating: 3.8,
        ratingCount: 0,
    },
    {
        id: 3,
        title: 'Tour 3',
        shortDescription: '',
        images: [],
        price: 0,
        priceForPerson: 0,
        duration: '',
        routeLength: 0,
        rating: 5.0,
        ratingCount: 0,
    },
    {
        id: 4,
        title: 'Tour 4',
        shortDescription: '',
        images: [],
        price: 0,
        priceForPerson: 0,
        duration: '',
        routeLength: 0,
        rating: 4.2,
        ratingCount: 0,
    },
]

const mockArray2: ITour[] = [
    {
        id: 1,
        title: 'Tour 1',
        shortDescription: '',
        images: [],
        price: 0,
        priceForPerson: 0,
        duration: '',
        routeLength: 0,
        rating: 4.5,
        ratingCount: 0,
    },
    {
        id: 2,
        title: 'Tour 2',
        shortDescription: '',
        images: [],
        price: 0,
        priceForPerson: 0,
        duration: '',
        routeLength: 0,
        rating: 3.8,
        ratingCount: 0,
    },
    {
        id: 3,
        title: 'Tour 3',
        shortDescription: '',
        images: [],
        price: 0,
        priceForPerson: 0,
        duration: '',
        routeLength: 0,
        rating: 3.0,
        ratingCount: 0,
    },
    {
        id: 4,
        title: 'Tour 4',
        shortDescription: '',
        images: [],
        price: 0,
        priceForPerson: 0,
        duration: '',
        routeLength: 0,
        rating: 2.2,
        ratingCount: 0,
    },
]

const singleMock: ITour[] = [
    {
        id: 5,
        title: '',
        shortDescription: '',
        images: [],
        price: 0,
        priceForPerson: 0,
        duration: '',
        routeLength: 0,
        rating: 4.9,
        ratingCount: 0,
    },
]

describe("sortByRating", () => {

    it("Сортирует массив по убыванию рейтинга", () => {
        const sorted = sortByRating([...mockArray])
        expect(sorted).toEqual([
            mockArray[2],
            mockArray[0],
            mockArray[3],
            mockArray[1],
        ])
    })

    it("Возвращает пустой массив при пустом входном массиве", () => {
        const sorted = sortByRating([])
        expect(sorted).toEqual([])
    })

    it("Корректно работает с массивом из одного элемента", () => {
        const sorted = sortByRating(singleMock)
        expect(sorted).toEqual(singleMock)
    })

    it("Не изменяет исходный массив", () => {
        let original = [...mockArray2]
        original = sortByRating(original)
        expect(original).toEqual(mockArray2)
    })

})