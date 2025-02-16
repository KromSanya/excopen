import {FC, ReactNode} from "react";

type ParamsContainerProps = {
    children: ReactNode
}

export const ParamsContainer: FC<ParamsContainerProps> = ({children}) => {
    return (
        <div className={"flex flex-row items-center gap-4"}>
            {children}
        </div>
    );
};