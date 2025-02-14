import axios, {AxiosError} from "axios";

export const isAxiosError = (e: unknown): e is AxiosError => {
    return axios.isAxiosError(e)
}