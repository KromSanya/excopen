import {FC, useState} from "react";
import {Star} from "lucide-react";
import {cn} from "@/app/lib/utils.ts";

type StarsProps = {
    rating: number
    setRating: (rating: number) => void
}

export const Index: FC<StarsProps> = ({rating, setRating}) => {

    const [hovered, setHovered] = useState<number>(0)

    return (
        <div className={"flex flex-row gap-2"}>
            {
                [1, 2, 3, 4, 5].map(index =>
                    <Star
                        width={24}
                        height={24}
                        className={cn(
                            "cursor-pointer transition-colors",
                            index <= (hovered || rating) ? "text-primary-0" : "text-gray-400"
                        )}
                        onClick={() => setRating(index)}
                        onMouseEnter={() => setHovered(index)}
                        onMouseLeave={() => setHovered(0)}
                    />
                )
            }
        </div>
    );
};