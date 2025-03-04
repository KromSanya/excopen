declare module '*.css';

declare module '*.svg' {
    import React from 'react';
    const ReactComponent: React.FC<React.SVGProps<SVGSVGElement>>;
    export { ReactComponent };
    const src: string;
    export default src;
}

declare module '*.png' {
    const value: string;
    export default value;
}