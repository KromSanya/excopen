import {FC} from "react";
import style from '@/app/styles/pages.module.css'
import {OnBoardingWidget} from "@/widgets";

export const OnBoardingPage: FC = () => {
    return (
        <div className={style.onboarding}>
            <OnBoardingWidget/>
        </div>
    );
};