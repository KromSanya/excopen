import {FC, ReactNode} from "react";

type CarouselContainerProps = {
    children: ReactNode
}

export const CarouselContainer: FC<CarouselContainerProps> = ({children}) => {
    return (
        <div className={"w-full group relative"}>
            {children}
        </div>
    );
};