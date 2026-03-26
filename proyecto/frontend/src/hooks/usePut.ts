import { useState } from "react";
import api from "../services/api";

interface PutState<T> {
    data: T | null;
    putError: string | null;
    isLoading: boolean;
    put: (body: unknown) => Promise<T | null>;
}

export const usePut = <T>(url: string): PutState<T> => {
    const [data, setData] = useState<T | null>(null);
    const [putError, setError] = useState<string | null>(null);
    const [isLoading, setIsLoading] = useState<boolean>(false);

    const put = async (body: unknown): Promise<T | null> => {
        setIsLoading(true);
        setError(null);
        try {
            const result = await api.put(url, body);
            setData(result);
            return result;
        } catch (e: any) {
            setError(e.message);
            return null;
        } finally {
            setIsLoading(false);
        }
    };

    return { data, putError, isLoading, put };
};