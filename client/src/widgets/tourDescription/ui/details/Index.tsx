import {FC} from "react";
import users from "@/shared/assets/icons/users.svg";
import back from "@/shared/assets/icons/back.svg";
import wallet from "@/shared/assets/icons/wallet.svg";
import {Item} from "./item";
import style from "./style.module.css"

export const Index: FC = () => {
    return (
        <div className={style.container}>
            <h2 className={style.heading}>
                Условия бронирования
            </h2>
            <div className={style.main}>
                <Item
                    title={"Групповой формат"}
                    description={"С вами будут другие участники, группа до 10 человек"}
                    image={users}
                />
                <Item
                    title={"Бесплатная отмена"}
                    description={"за 48 часов"}
                    image={back}
                />
                <Item
                    title={"Предоплата 25%,"}
                    description={"остальное – на месте"}
                    image={wallet}
                />
            </div>
        </div>
    );
};