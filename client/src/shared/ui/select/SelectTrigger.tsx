import * as React from "react";
import {useId} from "react";
import * as SelectPrimitive from "@radix-ui/react-select";
import {cn} from "@/app/lib/utils.ts";
import {SelectType} from "@/shared/ui";
import users from "@/shared/assets/icons/users.svg";
import {ChevronDownIcon} from "@radix-ui/react-icons";
import {SelectLabel} from "@/shared/ui/select/SelectLabel.tsx";
import {SelectGroup} from "@/shared/ui/select/SelectGroup.tsx";

type SelectTriggerProps = React.ComponentPropsWithoutRef<typeof SelectPrimitive.Trigger> & {
    selectType: SelectType;
    placeholder: string;
    isFormClick?: boolean;
};

export const SelectTrigger = React.forwardRef<
    React.ElementRef<typeof SelectPrimitive.Trigger>,
    SelectTriggerProps
>((
    {
        className,
        selectType,
        placeholder,
        isFormClick,
        value,
        ...props
    },
    ref
) => {

    const id: string = useId()

    const containerStyles: string = [
        "flex flex-col relative rounded-xl",
        !value && isFormClick && "border border-secondary-red"
    ].join(" ")
    const iconStyles: string = "h-5 w-5 mr-3"

    const placeholderStyles: string = [
        "absolute left-14 transition-all duration-300",
        "text-base text-grayscale-400 cursor-pointer"
    ].join(" ")

    const triggerStyles: string = [
        "flex items-center data-[state=open]:bg-grayscale-0",
        "transition-all duration-300 rounded-xl outline-none",
        "data-[state=open]:border data-[state=open]:border-grayscale-400",
        !value && isFormClick && "data-[state=open]:border-secondary-red"
    ].join(" ")

    const sortTypeStyles: string = [
        "justify-between h-10 w-40 bg-grayscale-0 px-3 py-2 text-sm"
    ].join(" ")
    const accessibilityTypeStyles: string = [
        "justify-start h-14 w-72 px-6 py-4 text-base transition-all duration-300",
        !value && isFormClick ? "bg-red-100" : "bg-grayscale-200"
    ].join(" ")

    return (
        <div className={containerStyles}>
            {
                selectType === SelectType.ACCESSIBILITY &&
                <label htmlFor={id} className={cn(placeholderStyles, value ? "top-2" : "top-4")}>
                    {placeholder}
                </label>
            }
            <SelectPrimitive.Trigger
                id={id}
                ref={ref}
                className={cn(
                    triggerStyles,
                    selectType === SelectType.ACCESSIBILITY ? accessibilityTypeStyles : sortTypeStyles,
                    className
                )}
                {...props}
            >
                {
                    selectType === SelectType.ACCESSIBILITY &&
                    <img alt={"users"} src={users} className={iconStyles}/>
                }
                {
                    <SelectGroup>
                        <SelectLabel className={selectType === SelectType.ACCESSIBILITY ? "mt-3" : ""}>
                            {selectType === SelectType.SORT && !value ? placeholder : value}
                        </SelectLabel>
                    </SelectGroup>
                }
                {
                    selectType === SelectType.SORT &&
                    <SelectPrimitive.Icon asChild>
                        <ChevronDownIcon className="h-4 w-4"/>
                    </SelectPrimitive.Icon>
                }
            </SelectPrimitive.Trigger>
        </div>
    )
});

SelectTrigger.displayName = SelectPrimitive.Trigger.displayName;