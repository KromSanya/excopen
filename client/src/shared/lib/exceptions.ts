export class ApiException<T> extends Error {

    public statusCode?: number;
    public data?: T | T[];

    constructor(message: string, statusCode?: number, data?: T | T[]) {
        super(message)
        this.name = 'ApiError'
        this.statusCode = statusCode
        this.data = data
    }

}