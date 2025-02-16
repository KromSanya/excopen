import logo from "@/shared/assets/images/logo.svg";
import { FC, useState } from "react";
import style from "./style.module.css";
import "./transition.css";
import { Reqs } from "./reqs";
import { Introduction } from "@/widgets/onboarding/ui/introduction/index.ts";

export const Index: FC = () => {

    const [clicked, setClicked] = useState<boolean>(false);

    return (
        <div className="relative overflow-hidden h-screen">
            <img className={style.bg} alt={"logo"} src={logo}/>
            <div className={`slide ${clicked ? "-translate-x-full" : "translate-x-0"}`}>
                <Introduction setClicked={setClicked}/>
            </div>
            <div className={`slide ${clicked ? "translate-x-0" : "translate-x-full"}`}>
                <Reqs/>
            </div>
        </div>
    );
};