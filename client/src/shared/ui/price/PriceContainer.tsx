import {FC, ReactNode} from "react";
import {cn} from "@/app/lib/utils.ts";
import {PriceContainerVariant} from "@/shared/types";

type PriceContainerProps = {
    children: ReactNode
    variant: PriceContainerVariant
}

export const PriceContainer: FC<PriceContainerProps> = ({children, variant}) => {
    return (
        <div className={cn(
            "flex flex-col",
            variant === PriceContainerVariant.MOBILE ? "md:hidden" : "max-md:hidden items-end"
        )}>
            {children}
        </div>
    );
};