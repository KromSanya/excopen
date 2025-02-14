import users from "@/shared/assets/icons/users.svg";
import {Select, SelectAccessibilityTrigger, SelectContent, SelectItem} from "@/shared/ui";
import {tourAccessibilityArray} from "@/features/searchTour/config";
import {useSearchContext} from "@/features";

export const Index = () => {

    const {context, setAccessibility} = useSearchContext()

    const label = tourAccessibilityArray.find(
        opt => opt.value === context.searchParams.accessibility
    )?.label

    return (
        <Select value={context.searchParams.accessibility} onValueChange={setAccessibility}>
            <SelectAccessibilityTrigger
                icon={users}
                isSearch={context.isSearch}
                value={label}
                placeholder={"Участники"}
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