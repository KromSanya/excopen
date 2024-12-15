import {FC} from "react";
import {useNavigate} from "react-router-dom";
import {Button} from "@/shared/ui";
import {Orientation, RouteNames} from "@/shared/types";

type SearchButtonProps = {
    location: string
    disabled: boolean
    orientation: Orientation
}

export const SearchButton: FC<SearchButtonProps> = ({location, disabled, orientation}) => {

    const navigate = useNavigate()

    const clickHandler = () => {
        if (disabled) navigate(`/${RouteNames.TOURS}/${encodeURIComponent(location)}`)
    }

    return (
        <Button
            onClick={clickHandler}
            size={orientation === Orientation.HORIZONTAL ? "default" : "lg"}
            disabled={!disabled}
        >
            Искать
        </Button>
    );

};