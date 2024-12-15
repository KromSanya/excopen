import {FC} from "react";
import {Input} from "@/shared/ui";
import style from "./style.module.css"

type IndexProps = {
    onChangeHandler: (value: string) => void
}

export const Index: FC<IndexProps> = ({onChangeHandler}) => {

    return (
        <div className={style.container}>
            <Input
                onChangeHandler={onChangeHandler}
                placeholder={"Искать"}
            />
            <div className={style.subContainer}>
                <span>
                    Вы смотрели ранее
                </span>
            </div>
        </div>
    );
};