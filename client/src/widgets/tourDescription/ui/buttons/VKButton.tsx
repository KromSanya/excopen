import {Link} from "react-router-dom";
import {FC} from "react";
import {Button} from "@/shared/ui";
import vk from "@/shared/assets/icons/vk.svg";

type VKButtonProps = {
    link?: string
}

export const VKButton: FC<VKButtonProps> = ({link = "/main"}) => {
    return (
        <Link to={link}>
            <Button size={"lgIcon"}>
                <img width={32} height={32} alt={"vk"} src={vk}/>
            </Button>
        </Link>
    );
};