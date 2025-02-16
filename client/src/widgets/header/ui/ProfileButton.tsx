import {
    Button, DropdownMenu,
    DropdownMenuContent,
    DropdownMenuGroup,
    DropdownMenuItem,
    DropdownMenuSeparator,
    DropdownMenuTrigger
} from "@/shared/ui";
import avatar from "@/shared/assets/icons/avatar.svg";
import profile from "@/shared/assets/icons/profile.svg";
import create from "@/shared/assets/icons/wallet.svg";
import star from "@/shared/assets/icons/star-gray.svg";
import next from "@/shared/assets/icons/next-secondary.svg";
import {FC, JSX} from "react";
import {RouteNames, UserRole} from "@/shared/types";
import {useAuthContext} from "@/app/context";

export const ProfileButton: FC = () => {

    const {role} = useAuthContext()

    const createTourLink: JSX.Element =
        <>
            <DropdownMenuSeparator/>
            <DropdownMenuItem className={"justify-between"} path={`/${RouteNames.CREATE}`}>
                <div className={"flex flex-row gap-2 items-center"}>
                    <img alt={"create"} src={create} height={16} width={16}/>
                    Предложить эк-ю
                </div>
                <img alt={"next"} src={next} height={16} width={16}/>
            </DropdownMenuItem>
        </>

    return (
        <DropdownMenu>
            <DropdownMenuTrigger asChild>
                <Button size={"sm"}>
                    Личный кабинет
                    <img alt={"avatar"} src={avatar} width={20} height={20}/>
                </Button>
            </DropdownMenuTrigger>
            <DropdownMenuContent side={"bottom"} align={"end"}>
                <DropdownMenuGroup>
                    <DropdownMenuItem path={`/${RouteNames.PROFILE}`}>
                        <img alt={"profile"} src={profile} height={16} width={16}/>
                        Профиль
                    </DropdownMenuItem>
                    <DropdownMenuItem path={`/${RouteNames.FAVOURITES}`}>
                        <img alt={"star"} src={star} height={16} width={16}/>
                        Избранное
                    </DropdownMenuItem>
                    {role === UserRole.contributor ? createTourLink : null}
                </DropdownMenuGroup>
            </DropdownMenuContent>
        </DropdownMenu>
    );
};