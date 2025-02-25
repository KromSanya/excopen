import {Button, ReviewInput} from "@/shared/ui";
import {FC, useEffect, useState} from "react";
import {Stars} from "./components";
import style from "./style.module.css"

export const Index: FC = () => {

    const [positive, setPositive] = useState<string>("")
    const [negative, setNegative] = useState<string>("")
    const [rating, setRating] = useState<number>(0)

    const [completed, setCompleted] = useState<boolean>(false)

    useEffect(() => {
        if (negative.length !== 0 && positive.length !== 0 && rating !== 0) setCompleted(true)
        else setCompleted(false)
    }, [negative, positive, rating]);

    const saveReview = () => {
        // сохрамения данных
        console.log(positive + " | " + negative + " | " + rating)
    }

    return (
        <div className={style.container}>
            <div className={style.header}>
                <span className={style.heading}>
                    Расскажите о ваших впечатлениях
                </span>
                <Stars rating={rating} setRating={setRating}/>
            </div>
            <div className={style.reviews}>
                <ReviewInput
                    className={"bg-grayscale-200"}
                    onChangeHandler={setPositive}
                    placeholder={"Что понравилось"}
                />
                <ReviewInput
                    className={"bg-grayscale-200"}
                    onChangeHandler={setNegative}
                    placeholder={"Что не понравилось"}
                />
            </div>
            <div className={style.button}>
                <Button disabled={!completed} onClick={saveReview}>
                    Добавить
                </Button>
            </div>
        </div>
    );
};