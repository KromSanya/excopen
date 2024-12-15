import {FC} from "react";
import {Button} from "@/shared/ui";
import style from "./style.module.css"
import {Text} from "./Text.tsx";
import {cn} from "@/app/lib/utils.ts";

type IntroductionProps = {
    setClicked: (value: boolean) => void
}

export const Index: FC<IntroductionProps> = ({setClicked}) => {

    const clickHandler = () => setClicked(true)

    return (
        <div className={cn(style.container, style.paddings)}>
            <Text/>
            <div className={style.button}>
                <Button onClick={clickHandler} size={"lg"}>
                    Готов путешествовать
                </Button>
            </div>
        </div>
    );
};