export class ApiException<T> extends Error {

    public statusCode?: number;
    public data?: T[];

    constructor(message: string, statusCode?: number, data?: T[]) {
        super(message); // Передаем сообщение в базовый класс Error
        this.name = 'ApiError';
        this.statusCode = statusCode;
        this.data = data;
    }

}