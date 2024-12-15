import {FC, ReactNode} from "react";

type CommandContainerProps = {
    isOpen?: boolean
    children: ReactNode
}

export const CommandContainer: FC<CommandContainerProps> = ({children, isOpen = false}) => {
    return (
        <div
            className={
                "absolute transition-all duration-500 ease-in-out transform " +
                (
                    isOpen
                        ? "w-72 opacity-100 translate-y-0"
                        : "opacity-0 translate-y-10 max-h-0"
                )
            }
        >
            {children}
        </div>
    );
};