import * as React from "react";

const Card = React.forwardRef<
    HTMLDivElement,
    React.HTMLAttributes<HTMLDivElement>
>(({...props }, ref) => (
    <div ref={ref} {...props}/>
))

Card.displayName = "Card"

export {Card}