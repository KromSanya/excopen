import bg from "@/shared/assets/tempData/assets/cardBg.png";
import bg2 from "@/shared/assets/tempData/assets/cardBg2.png";
import bg3 from "@/shared/assets/tempData/assets/cardBg3.png";
import bg4 from "@/shared/assets/tempData/assets/cardBg4.png";
import {ILocation} from "@/shared/types";

export const locationsArray: ILocation[] = [
    {
        id: 0,
        country: "Россия",
        city: "Омск",
        tourCount: 100,
        image: bg,
    },
    {
        id: 1,
        country: "Россия",
        city: "Санкт-Петербург",
        tourCount: 200,
        image: bg2,
    },
    {
        id: 2,
        country: "Россия",
        city: "Новосибирск",
        tourCount: 300,
        image: bg3,
    },
    {
        id: 3,
        country: "Россия",
        city: "Москва",
        tourCount: 400,
        image: bg4,
    },
]