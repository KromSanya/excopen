import {useEffect, useState} from "react";
import {Orientation} from "@/shared/types";
import {useWindowSize} from "usehooks-ts";

export const useOrientation = (value: Orientation): Orientation => {

    const [orientation, setOrientation] = useState<Orientation>(Orientation.VERTICAL)
    const {width} = useWindowSize()

    useEffect(() => {

        const updateOrientation = () => {
            if (value === Orientation.VERTICAL) setOrientation(value)
            else {
                if (width < 500) setOrientation(Orientation.VERTICAL)
                else setOrientation(Orientation.HORIZONTAL)
            }
        }

        updateOrientation()

    }, [value, width]);

    return orientation;

}