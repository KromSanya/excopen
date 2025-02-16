import {FC} from "react";
import {Link} from "react-router-dom";
import {Button} from "@/shared/ui";

type ContactButtonProps = {
    link: string
}

export const ContactButton: FC<ContactButtonProps> = ({link}) => {
    return (
        <Link to={link}>
            <Button size={"lg"}>
                Связаться
            </Button>
        </Link>
    );
};