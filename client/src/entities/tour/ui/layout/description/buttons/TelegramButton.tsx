import {Link} from "react-router-dom";
import {FC} from "react";
import telegram from "@/shared/assets/icons/telegram.svg";
import {Button} from "@/shared/ui";

type TelegramButtonProps = {
    link?: string
}

export const TelegramButton: FC<TelegramButtonProps> = ({link = "/main"}) => {
    return (
        <Link to={link}>
            <Button size={"lgIcon"}>
                <img width={32} height={32} alt={"telegram"} src={telegram}/>
            </Button>
        </Link>
    );
};