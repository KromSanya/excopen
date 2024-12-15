import { FC, useState } from "react";
import style from "./style.module.css";
import { cn } from "@/app/lib/utils.ts";
import { lettersArray } from "@/widgets/searchBar/utils";

type IndexProps = {
    onChange: (value: string) => void
    isActive: boolean
};

export const Index: FC<IndexProps> = ({ onChange, isActive }) => {

    const [value, setValue] = useState<string>("А");

    const clickHandler = (newValue: string) => {
        setValue(newValue)
        onChange(value)
    }

    return (
        <div className={style.container}>
            {lettersArray.map((letter) => (
                <span
                    key={letter}
                    onClick={() => clickHandler(letter)}
                    className={cn(
                        value === letter && !isActive ? style.active : style.letter,
                        letter !== "Я" && style.border
                    )}
                >
                    {letter}
                </span>
            ))}
        </div>
    );
};