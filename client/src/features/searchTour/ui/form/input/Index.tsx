import * as React from "react";
import { FC, useRef, useState } from "react";
import { useOnClickOutside } from "usehooks-ts";
import {
    Command,
    CommandContainer,
    CommandEmpty,
    CommandGroup,
    CommandInput,
    CommandList,
    CommandLocation,
} from "@/shared/ui";
import { validateByCity } from "@/features/searchTour/lib";
import { useSearchContext } from "@/features";
import { locationsArray } from "@/shared/assets/tempData/locationsArray.ts";

export const Index: FC = () => {

    const { searchParams, setLocation, isSearch } = useSearchContext();

    const [field, setField] = useState({
        isOpen: false,
        isTouched: false,
        isCorrectedField: true,
    });

    const commandRef = useRef<HTMLDivElement>(null);

    const updateField = (newValue: string) => {
        setLocation(newValue);
        setField({
            isTouched: true,
            isOpen: true,
            isCorrectedField: validateByCity(newValue),
        });
    };

    const clickInputHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
        const inputValue = e.target.value;
        updateField(inputValue);
    };

    const clickCommandItemHandler = (city: string) => {
        updateField(city);
        setField((prev) => ({ ...prev, isOpen: false }));
    };

    const focusHandler = () => {
        setField((prev) => ({ ...prev, isOpen: true }));
    };

    const blurHandler = () => {
        if (!field.isOpen && searchParams.location === "") {
            setField((prev) => ({ ...prev, isTouched: true }));
        }
    };

    const clearValue = () => {
        setLocation("");
    };

    useOnClickOutside(commandRef, () => {
        setField((prev) => ({
            ...prev,
            isOpen: false,
            isTouched: searchParams.location === "" ? false : prev.isTouched,
        }));
    });

    return (
        <Command role={"form-command"} ref={commandRef}>
            <CommandInput
                data-is-corrected={field.isCorrectedField ? "true" : "false"}
                isSearch={isSearch}
                field={field}
                value={searchParams.location}
                label={"Где искать"}
                onClear={clearValue}
                onChangeCapture={clickInputHandler}
                onFocus={focusHandler}
                onBlur={blurHandler}
            />
            <CommandContainer isOpen={field.isOpen}>
                <CommandList>
                    <CommandEmpty>Направления не найдены</CommandEmpty>
                    <CommandGroup heading={"Направления"}>
                        {locationsArray.map((location) => (
                            <CommandLocation
                                key={location.city}
                                onClick={() => clickCommandItemHandler(location.city)}
                                location={location}
                            />
                        ))}
                    </CommandGroup>
                </CommandList>
            </CommandContainer>
        </Command>
    );
};