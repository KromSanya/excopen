import {FC, useState} from "react";
import style from "./styles/style.module.css"
import {TagsArray} from "@/shared/assets/tempData/TagsArray.ts";
import {Tag} from "./Tag.tsx";
import {Text} from "./Text.tsx";
import {Buttons} from "./Buttons.tsx";
import {cn} from "@/app/lib/utils.ts";

export const Index: FC = () => {

    const [tagsId, setTagsId] = useState<number[]>([])

    const disabled = tagsId.length === 0

    return (
        <div className={cn(style.container, style.paddings)}>
            <Text/>
            <div className={style.tags}>
                {TagsArray.map(tag => (
                    <Tag
                        key={tag.value}
                        tagsId={tagsId}
                        setTagsId={setTagsId}
                        value={tag.value}
                        label={tag.label}
                    />
                ))}
            </div>
            <Buttons disabled={disabled}/>
        </div>
    );
};