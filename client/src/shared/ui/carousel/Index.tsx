import {Card, CardContent} from "@/shared/ui/card";
import {FC} from "react";
import {CarouselContainer} from "@/shared/ui/carousel/CarouselContainer.tsx";
import {CarouselContent} from "@/shared/ui/carousel/CarouselContent.tsx";
import {CarouselItem} from "@/shared/ui/carousel/CarouselItem.tsx";
import {CarouselPrevious} from "@/shared/ui/carousel/CarouselPrevious.tsx";
import {CarouselNext} from "@/shared/ui/carousel/CarouselNext.tsx";
import {Carousel} from "@/shared/ui/carousel/Carousel.tsx";
import {CarouselVariant} from "@/shared/ui/carousel/CarouselVariant.ts";
import {cn} from "@/app/lib/utils.ts";

type CarouselProps = {
    variant?: CarouselVariant
    images: string[]
}

export const Index: FC<CarouselProps> = ({variant = CarouselVariant.LARGE, images}) => {
    return (
        <CarouselContainer>
            <Carousel>
                <CarouselContent>
                    {images.map(image => (
                        <CarouselItem key={image} className={
                            variant === CarouselVariant.SMALL ? "basis-1/3 xl:basis-1/4" : "basis-1/3"
                        }>
                            <Card
                                className={cn("bg-card",
                                    variant === CarouselVariant.SMALL ? "" : "mx-1"
                                )}
                            >
                                <CardContent
                                    className={cn("bg-cover",
                                        variant === CarouselVariant.SMALL ? "" : "rounded-2xl"
                                    )}
                                    style={{
                                        backgroundImage: `url(${image})`
                                    }}
                                >
                                </CardContent>
                            </Card>
                        </CarouselItem>
                    ))}
                </CarouselContent>
                <CarouselPrevious/>
                <CarouselNext/>
            </Carousel>
        </CarouselContainer>
    );
};