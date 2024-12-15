import {FC} from "react";
import {PriceContainer} from "@/shared/ui/price/PriceContainer.tsx";
import {PriceDescription} from "@/shared/ui/price/PriceDescription.tsx";
import {Price} from "@/shared/ui/price/PriceValue.tsx";
import {PriceContainerVariant} from "@/shared/ui";

type GroupPriceProps = {
    price: number
    variant?: PriceContainerVariant
}

export const GroupPrice: FC<GroupPriceProps> = ({price, variant = PriceContainerVariant.DESkTOP}) => {
    return (
        <PriceContainer variant={variant}>
            <PriceDescription/>
            <Price value={price}/>
        </PriceContainer>
    );
};