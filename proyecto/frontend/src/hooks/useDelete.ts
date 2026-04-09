import { useState } from "react";
import api from "../services/api";

interface DeleteState<T> {
    data: T | null;
    deleteError: string | null;
    isLoading: boolean;
    constDelete: () => Promise<T | null>;
}

export const useDelete = <T>(url: string): DeleteState<T> => {
    const [data, setData] = useState<T | null>(null);
    const [deleteError, setError] = useState<string | null>(null);
    const [isLoading, setIsLoading] = useState<boolean>(false);

    const constDelete = async (): Promise<T | null> => {
        setIsLoading(true);
        setError(null);
        try {
            const result = await api.delete(url);
            setData(result);
            return result;
        } catch (e: any) {
            setError(e.message);
            return null;
        } finally {
            setIsLoading(false);
        }
    };

    return { data, deleteError, isLoading, constDelete };
};