import {FC} from "react";
import {useSearchTourContext} from "@/features";
import {Orientation, TourAccessibility} from "@/shared/types";
import {tourAccessibilityArray} from "@/widgets/form/utils";
import {Container, Header, Bottom} from "./containers";
import {DatePicker, Heading, Input, SearchButton, SwitchByCity} from "./components";
import {SelectParams, SelectType} from "@/shared/ui";

type FormProps = {
    orientation: Orientation
}

export const Index: FC<FormProps> = ({orientation}) => {

    const {setLocation, setAccessibility, setByCity, setDate, searchParams} = useSearchTourContext()

    const disabled = !!searchParams.location && !!searchParams.date

    return (
        <Container orientation={orientation}>
            <Header orientation={orientation}>
                <Heading value={"Куда теперь?"} orientation={orientation}/>
                <Input onChangeValue={setLocation}/>
                <DatePicker onChange={setDate}/>
                <SelectParams
                    defaultValue={TourAccessibility.WITHOUT_CHILDREN}
                    onChangeValue={setAccessibility}
                    options={tourAccessibilityArray}
                    placeholder={"Участники"}
                    selectType={SelectType.ACCESSIBILITY}
                />
                {
                    orientation === Orientation.VERTICAL
                        ?
                        <Bottom orientation={orientation}>
                            <SwitchByCity onChangeValue={setByCity} />
                        </Bottom>
                        :
                        null
                }
                <SearchButton
                    location={searchParams.location}
                    orientation={orientation}
                    disabled={disabled}
                />
            </Header>
            {
                orientation === Orientation.HORIZONTAL
                    ?
                    <Bottom orientation={orientation}>
                        <SwitchByCity onChangeValue={setByCity} />
                    </Bottom>
                    :
                    null
            }
        </Container>
    );
};