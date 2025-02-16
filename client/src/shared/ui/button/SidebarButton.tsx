import {FC} from "react";
import {Link} from "react-router-dom";
import {RouteNames} from "@/shared/types";
import next from "@/shared/assets/icons/next-secondary.svg";
import {Button} from "@/shared/ui";

type SidebarButtonProps = {
    label: string,
    image: string
}

export const SidebarButton: FC<SidebarButtonProps> = ({label, image}) => {
    return (
        <Button className={"w-64"} variant={"secondary"} size={"md"}>
            <img
                height={24}
                width={24}
                alt={"favourite"}
                src={image}
            />
            {label}
            <Link to={`/${RouteNames.FAVOURITES}`}>
                <img
                    height={20}
                    width={20}
                    alt={"arrow"}
                    src={next}
                />
            </Link>
        </Button>
    );
};