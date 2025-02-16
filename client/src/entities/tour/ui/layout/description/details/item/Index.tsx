import {FC} from "react";
import {cn} from "@/app/lib/utils.ts";
import style from "./style.module.css";

type ItemProps = {
    title: string
    description: string
    image: string
}

export const Index: FC<ItemProps> = ({title, image, description}) => {
    return (
        <div className={cn(style.container, style.bg, style.sizes, style.paddings)}>
            <div className={style.image}>
                <img width={20} height={20} alt={"item"} src={image}/>
            </div>
            <div className={style.content}>
                <span className={style.heading}>
                    {title}
                </span>
                <p>
                    {description}
                </p>
            </div>
        </div>
    );
};