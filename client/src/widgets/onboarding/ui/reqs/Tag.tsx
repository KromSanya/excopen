import {FC, useState} from "react";
import {Button} from "@/shared/ui";

type TagProps = {
    value: number
    label: string
    tagsId: number[]
    setTagsId: (value: number[]) => void
}

export const Tag: FC<TagProps> = ({value, label, setTagsId, tagsId}) => {

    const [clicked, setClicked] = useState<boolean>(false)

    const clickHandler = () => {

        setClicked(!clicked)

        if (!tagsId.includes(value)) {
            setTagsId([...tagsId, value])
        } else {
            setTagsId(tagsId.filter(id => id !== value))
        }

    }

    return (
        <Button
            onClick={clickHandler}
            className={clicked ? "bg-primary-0" : "bg-grayscale-300"}
            variant={"secondary"}
            size={"md"}
        >
            {label}
        </Button>
    );
};