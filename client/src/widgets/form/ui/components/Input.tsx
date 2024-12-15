import {
    Command,
    CommandContainer,
    CommandEmpty,
    CommandGroup,
    CommandInput,
    CommandItem,
    CommandList,
} from "@/shared/ui";
import * as React from "react";
import {FC, useEffect, useRef, useState} from "react";
import {locationsArray} from "@/widgets/form/utils";
import {useOnClickOutside} from "usehooks-ts";

type InputProps = {
    onChangeValue: (value: string) => void;
};

export const Input: FC<InputProps> = ({ onChangeValue }) => {

    const [isOpen, setIsOpen] = useState(false);
    const [value, setValue] = useState<string>("");

    const commandRef = useRef<HTMLDivElement>(null);

    const clickHandler = (e: React.ChangeEvent<HTMLInputElement>) => setValue(e.target.value);
    //const [debouncedValue] = useDebounceValue<string>(value, 300);

    const clickItemHandler = (newValue: string) => {
        setValue(newValue)
        setIsOpen(false)
    }

    useEffect(() => {
        onChangeValue(value);
    }, [value, onChangeValue]);

    useOnClickOutside(commandRef, () => setIsOpen(false))

    return (
        <Command ref={commandRef}>
            <CommandInput
                value={value}
                onChangeCapture={clickHandler}
                placeholder="Где искать"
                onFocus={() => setIsOpen(true)}
            />
            <CommandContainer
                isOpen={isOpen}
            >
                <CommandList>
                    <CommandEmpty>
                        Направления не найдены
                    </CommandEmpty>
                    <CommandGroup heading="Направления">
                        {locationsArray.map(location => (
                            <div key={location} onClick={() => clickItemHandler(location)}>
                                <CommandItem>
                                    <span>{location}</span>
                                </CommandItem>
                            </div>
                        ))}
                    </CommandGroup>
                </CommandList>
            </CommandContainer>
        </Command>
    );
};