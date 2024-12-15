import {FC} from "react";
import {Button} from "@/shared/ui";
import chat from "@/shared/assets/icons/comment.svg";

export const SupportButton: FC = () => {
    return (
        <div className={"flex w-full justify-end px-6"}>
            <Button size={"roundedIcon"}>
                <img alt={"chat"} src={chat} width={28} height={24}/>
            </Button>
        </div>
    );
};