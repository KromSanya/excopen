import '@testing-library/jest-dom';

window.HTMLElement.prototype.hasPointerCapture = () => false

Object.defineProperty(window.HTMLElement.prototype, 'scrollIntoView', {
    writable: true,
    value: vi.fn(),
});

global.ResizeObserver = class {
    observe() {}
    unobserve() {}
    disconnect() {}
};