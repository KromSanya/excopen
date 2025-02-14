import {FC} from "react";
import {SearchInput} from "@/shared/ui";
import style from "./style.module.css"

type IndexProps = {
    onChangeHandler: (value: string) => void
}

export const Index: FC<IndexProps> = ({onChangeHandler}) => {

    return (
        <div className={style.container}>
            <SearchInput
                className={"max-md:w-full"}
                onChangeHandler={onChangeHandler}
                placeholder={"Искать по городу"}
            />
            <div className={style.subContainer}>
                <span>
                    Вы смотрели ранее
                </span>
            </div>
        </div>
    );
};