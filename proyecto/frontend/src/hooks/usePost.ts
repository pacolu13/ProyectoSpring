// hooks/usePost.ts
import { useState } from "react";
import { useAuth } from "../context/AuthContext";
import api from "../services/api";

interface PostState<T> {
    data: T | null;
    error: string | null;
    isLoading: boolean;
    post: (body: unknown) => Promise<T | null>;
}

export const usePost = <T>(url: string): PostState<T> => {
    const { login } = useAuth();
    const [data, setData] = useState<T | null>(null);
    const [error, setError] = useState<string | null>(null);
    const [isLoading, setIsLoading] = useState<boolean>(false);

    const post = async (body: unknown): Promise<T | null> => {
        setIsLoading(true);
        setError(null);
        try {
            const result = await api.post(url, body);
            setData(result);
            return result;
        } catch (e: any) {
            setError(e.message);
            return null;
        } finally {
            setIsLoading(false);
        }
    };

    return { data, error, isLoading, post };
};