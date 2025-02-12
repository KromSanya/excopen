import * as React from "react";
import {Command as CommandPrimitive} from "cmdk";
import {cn} from "@/app/lib/utils.ts";
import {ComponentPropsWithoutRef, ElementRef} from "react";
import {SearchIcon, X} from "lucide-react";

export const CommandInput = React.forwardRef<
    ElementRef<'div'>,
    ComponentPropsWithoutRef<typeof CommandPrimitive.Input> & {
    label: string;
    isSearch: boolean;
    field: {
        isOpen: boolean;
        isTouched: boolean;
        isCorrectedField: boolean;
    },
    onClear: () => void
}
>((
        {
            className,
            label,
            isSearch,
            field,
            onClear,
            value,
            ...props
        },
        ref
    ) => {

    const inputRef = React.useRef<HTMLInputElement>(null)
    const handleDivClick = () => inputRef.current?.focus()

    const isValidField = (field.isTouched && !value) || !field.isCorrectedField || (isSearch && !value)

    const containerStyles: string = "relative flex flex-col w-full wide:w-72 cursor-pointer";
    const wrapperStyles: string = "flex items-center";
    const iconSearchStyles: string = "absolute left-4 h-5 w-5 text-gray-500";
    const iconXStyles: string = "absolute right-4 h-5 w-5 text-gray-500 cursor-pointer";
    const inputStyles = [
        "h-14 w-full bg-grayscale-200 hover:bg-grayscale-300 rounded-xl px-6 pt-4 text-base flex items-center",
        "placeholder-transparent transition duration-300",
        "focus:bg-white focus:outline-none",
        "pr-8 peer pl-12 cursor-pointer",
        field.isOpen ? "focus:ring-1 focus:ring-black" : "ring-transparent",
        isValidField ? "ring-1 ring-secondary-red focus:ring-secondary-red bg-red-100" : "",
        "flex items-center justify-center"
    ].join(" ");
    const labelStyles = [
        "absolute cursor-pointer left-12 text-base text-grayscale-400 transition-all duration-300",
        value || field.isOpen ? "top-2 text-black" : "top-4 text-base text-grayscale-400",
    ].join(" ");

    return (
        <div
            className={containerStyles}
            onClick={handleDivClick}
            ref={ref}
        >
            <div className={wrapperStyles}>
                <SearchIcon className={iconSearchStyles}/>
                <CommandPrimitive.Input
                    ref={inputRef}
                    className={cn(inputStyles, className)}
                    value={value}
                    placeholder=" "
                    {...props}
                />
                {value && <X onClick={onClear} className={iconXStyles}/>}
            </div>
            <label htmlFor="command-input" className={labelStyles}>
                {label}
            </label>
        </div>
    );
});

CommandInput.displayName = CommandPrimitive.Input.displayName;