import {FC} from "react";
import {useNavigate} from "react-router-dom";
import {Button} from "@/shared/ui";
import {Orientation, RouteNames} from "@/shared/types";
import {useSearchContext} from "@/features";

type SearchButtonProps = {
    location: string
    disabled: boolean
    orientation: Orientation
}

export const Index: FC<SearchButtonProps> = (
    {location, disabled, orientation}
) => {

    const {setIsSearch} = useSearchContext()

    const navigate = useNavigate()

    const clickHandler = () => {
        setIsSearch(true)
        if (disabled) navigate(`/${RouteNames.TOURS}/${encodeURIComponent(location)}`)
    }

    return (
        <Button
            role={"searchButton"}
            onClick={clickHandler}
            size={orientation === Orientation.HORIZONTAL ? "default" : "lg"}
        >
            Искать
        </Button>
    );

};