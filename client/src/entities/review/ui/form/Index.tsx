import {Button, ReviewInput} from "@/shared/ui";
import {useEffect, useState} from "react";
import {Stars} from "./stars";

export const Index = () => {

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
        <div className={"flex flex-col p-4 gap-4 rounded-2xl bg-grayscale-0 w-full"}>
            <div className={"flex flex-col md:flex-row justify-between"}>
                <span className={"text-xl text-grayscale-500 font-medium"}>
                    Расскажите о ваших впечатлениях
                </span>
                <Stars rating={rating} setRating={setRating}/>
            </div>
            <div className={"flex flex-col gap-2"}>
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
            <div className={"flex justify-end"}>
                <Button disabled={!completed} onClick={saveReview}>
                    Добавить
                </Button>
            </div>
        </div>
    );
};