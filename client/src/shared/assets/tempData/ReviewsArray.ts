import {IReview} from "@/shared/types";

export const reviewsArray: IReview[] = [
    {
        id: 1,
        name: "Макс",
        rating: 4.5,
        positiveText: "Экскурсия была потрясающей! Гид рассказал много интересных фактов, маршрут удобный, виды просто шикарные.",
        negativeText: "Хотелось бы больше времени на фотосессии.",
        withChildren: true,
        personCount: 4
    },
    {
        id: 2,
        name: "Алексей",
        rating: 4.1,
        positiveText: "Хорошо организованная экскурсия, приятный и знающий гид.",
        negativeText: "Автобус был не очень удобным, мало места для ног.",
        withChildren: false,
        personCount: 2
    },
    {
        id: 3,
        name: "Андрей",
        rating: 3.6,
        positiveText: "Интересный маршрут, красивые локации.",
        negativeText: "Гид говорил слишком быстро, трудно было понять информацию.",
        withChildren: true,
        personCount: 3
    },
    {
        id: 4,
        name: "Виктор",
        rating: 2.7,
        positiveText: "Маршрут соответствовал описанию, без сюрпризов.",
        negativeText: "Экскурсия затянулась, было скучновато, а автобус приехал с опозданием.",
        withChildren: false,
        personCount: 1
    },
    {
        id: 5,
        name: "Василий",
        rating: 1.8,
        positiveText: "Единственное, что понравилось – природа вокруг.",
        negativeText: "Гид неинтересно рассказывал, задержки на каждом этапе, организаторы не предупредили о сложном маршруте.",
        withChildren: false,
        personCount: 2
    }
]