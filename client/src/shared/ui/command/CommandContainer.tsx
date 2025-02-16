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
                        ? "w-4/5 wide:w-72 opacity-100 translate-y-0 z-50"
                        : "opacity-0 translate-y-10 max-h-0 -z-10"
                )
            }
        >
            {children}
        </div>
    );
};