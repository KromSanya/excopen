import {FC} from "react";
import {Link} from "react-router-dom";
import {Button} from "@/shared/ui";

type BookingButtonProps = {
    link: string
}

export const BookingButton: FC<BookingButtonProps> = ({link}) => {
    return (
        <Link to={link}>
            <Button size={"lg"}>
                Забронировать место(а)
            </Button>
        </Link>
    );
};