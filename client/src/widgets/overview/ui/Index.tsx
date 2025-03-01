import {FC} from "react";
import style from "./style.module.css"
import {Form} from "@/features";
import {Orientation} from "@/shared/types";
import {Locations} from "@/entities";
import {Title} from "./title";

export const Index: FC = () => {
    return (
        <section className={style.container}>
            <Title/>
            <Form orientation={Orientation.HORIZONTAL}/>
            <Locations/>
        </section>
    );
};