import {ContributorButton} from "@/widgets/sidebar/ui/contributorButton/index.ts";
import {Item} from "@/widgets/sidebar/ui/item/index.ts";
import {Rating} from "@/widgets/sidebar/ui/rating/index.ts";
import {Price} from "@/widgets/sidebar/ui/price/index.ts";
import {FC} from "react";
import {ITour, RouteNames} from "@/shared/types";
import style from "./style.module.css"
import {BookingButton} from "@/shared/ui";

type SidebarProps = {
    tour: ITour
}

export const Index: FC<SidebarProps> = ({tour}) => {

    return (
        <div className={style.container}>
            <ContributorButton
                name={tour.contributor?.name}
                link={`/${RouteNames.MAIN}`}
            />
            <div className={style.subContainer}>
                <Item option={"Формат:"} value={tour.format}/>
                <Item option={"Длительность:"} value={tour.duration}/>
                <Item option={"Размер группы:"} value={`${tour.groupCapacity} человек`}/>
                <Item option={"Формат проведения:"} value={tour.formatBehavior}/>
                <Rating option={"Рейтинг:"} rating={tour.rating} ratingCount={tour.ratingCount}/>
                <hr className={style.separator}/>
                <Price price={tour.priceForPerson}/>
                <BookingButton link={`/${RouteNames.MAIN}`}/>
            </div>
        </div>
    );
};