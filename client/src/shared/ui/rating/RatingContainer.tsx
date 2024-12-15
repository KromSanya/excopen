import {FC, ReactNode} from "react";

type RatingContainerProps = {
    children: ReactNode
}

export const RatingContainer: FC<RatingContainerProps> = ({children}) => {
    return (
        <div className={"flex flex-row gap-2"}>
            {children}
        </div>
    );
};