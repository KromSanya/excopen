import {describe} from "vitest";
import {ITour} from "@/shared/types";
import {sortByPrice, SortByPriceType} from "@/features";

const mockArray: ITour[] = [
    {
        id: 1,
        title: 'Tour 1',
        shortDescription: '',
        images: [],
        price: 150,
        priceForPerson: 0,
        duration: '',
        routeLength: 0,
        rating: 0,
        ratingCount: 0,
    },
    {
        id: 2,
        title: 'Tour 2',
        shortDescription: '',
        images: [],
        price: 300,
        priceForPerson: 0,
        duration: '',
        routeLength: 0,
        rating: 0,
        ratingCount: 0,
    },
    {
        id: 3,
        title: 'Tour 3',
        shortDescription: '',
        images: [],
        price: 100,
        priceForPerson: 0,
        duration: '',
        routeLength: 0,
        rating: 0,
        ratingCount: 0,
    },
    {
        id: 4,
        title: 'Tour 4',
        shortDescription: '',
        images: [],
        price: 300,
        priceForPerson: 0,
        duration: '',
        routeLength: 0,
        rating: 0,
        ratingCount: 0,
    },
]

const mockArray2: ITour[] = [
    {
        id: 1,
        title: 'Tour 1',
        shortDescription: '',
        images: [],
        price: 100,
        priceForPerson: 0,
        duration: '',
        routeLength: 0,
        rating: 0,
        ratingCount: 0,
    },
    {
        id: 2,
        title: 'Tour 2',
        shortDescription: '',
        images: [],
        price: 200,
        priceForPerson: 0,
        duration: '',
        routeLength: 0,
        rating: 0,
        ratingCount: 0,
    },
    {
        id: 3,
        title: 'Tour 3',
        shortDescription: '',
        images: [],
        price: 300,
        priceForPerson: 0,
        duration: '',
        routeLength: 0,
        rating: 0,
        ratingCount: 0,
    },
    {
        id: 4,
        title: 'Tour 4',
        shortDescription: '',
        images: [],
        price: 400,
        priceForPerson: 0,
        duration: '',
        routeLength: 0,
        rating: 0,
        ratingCount: 0,
    },
]

const singleMock: ITour[] = [
    {
        id: 5,
        title: '',
        shortDescription: '',
        images: [],
        price: 400,
        priceForPerson: 0,
        duration: '',
        routeLength: 0,
        rating: 0,
        ratingCount: 0,
    },
]

describe("sortByPrice", () => {

    it('Сортировка по возрастанию', () => {
        const sorted = sortByPrice([...mockArray], SortByPriceType.ASCENDING)
        expect(sorted).toEqual([
            mockArray[2],
            mockArray[0],
            mockArray[1],
            mockArray[3],
        ])
    })

    it('Сортировка по убыванию', () => {
        const sorted = sortByPrice([...mockArray], SortByPriceType.DESCENDING)
        expect(sorted).toEqual([
            mockArray[1],
            mockArray[3],
            mockArray[0],
            mockArray[2],
        ])
    })

    it('Обработка неимененного массива', () => {
        let original = [...mockArray2]
        original = sortByPrice(original, SortByPriceType.ASCENDING)
        expect(original).toEqual(mockArray2)
    });

    it('Обработка пустого массива', () => {
        const sorted = sortByPrice([], SortByPriceType.ASCENDING)
        expect(sorted).toEqual([])
    })

    it('Обработка массива с одним элементом', () => {
        const sorted = sortByPrice(singleMock, SortByPriceType.ASCENDING)
        expect(sorted).toEqual(singleMock)
    })

    it('Тест на возврат нового экземпляра массива', () => {
        const sorted = sortByPrice([...mockArray], SortByPriceType.ASCENDING)
        expect(sorted).not.toBe(mockArray)
    })

})