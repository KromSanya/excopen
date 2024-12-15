import {FC} from "react";
import style from "./style.module.css"
import avatar from "@/shared/assets/icons/avatar.svg"
import edit from "@/shared/assets/icons/edit.svg"

export const Index: FC = () => {
    return (
        <div className={style.container}>
            <img alt={"avatar"} width={40} height={40} src={avatar}/>
            <span className={style.name}>
                Андрей Ш.
            </span>
            <button>
                <img alt={"edit"} width={24} height={24} src={edit}/>
            </button>
        </div>
    );
};