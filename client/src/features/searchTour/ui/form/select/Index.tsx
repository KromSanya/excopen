import users from "@/shared/assets/icons/users.svg";
import {Select, SelectAccessibilityTrigger, SelectContent, SelectItem} from "@/shared/ui";
import {tourAccessibilityArray} from "@/features/searchTour/config";
import {useSearchContext} from "@/features";
import {useState} from "react";

export const Index = () => {

    const {context, setAccessibility} = useSearchContext()
    const [isTouched, setIsTouched] = useState<boolean>(false)

    const label = tourAccessibilityArray.find(
        opt => opt.value === context.searchParams.accessibility
    )?.label

    const focusHandler = () => setIsTouched(true)

    return (
        <Select value={context.searchParams.accessibility} onValueChange={setAccessibility}>
            <SelectAccessibilityTrigger
                icon={users}
                isSearch={context.isSearch}
                value={label}
                placeholder={"Участники"}
                onFocus={focusHandler}
                isTouched={isTouched}
            />
            <SelectContent side={"bottom"}>
                {tourAccessibilityArray.map((tour) =>
                    <SelectItem key={tour.value} value={String(tour.value)}>
                        {tour.label}
                    </SelectItem>
                )}
            </SelectContent>
        </Select>
    );
};