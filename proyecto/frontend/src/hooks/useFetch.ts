import { useEffect, useState } from "react";

type Data<T> = T | null;
type Error = string | null;

interface Params<T> {
    data: Data<T>;
    error: Error;
    isLoading: boolean;
}

export const useFetch = <T>(url: string): Params<T> => {
    const [data, setData] = useState<Data<T>>(null);
    const [error, setError] = useState<Error>(null);
    const [isLoading, setIsLoading] = useState<boolean>(true);

    useEffect(() => {
        const controller = new AbortController();

        setIsLoading(true);

        const fetchData = async () => {
            try {
                const response = await fetch(url, controller);

                if (!response.ok) {
                    throw new Error('Error en la petición');
                }

                const result = await response.json();
                setData(result);
            } catch (error) {
                setError(error as Error);
            } finally {
                setIsLoading(false);
            }
        };

        fetchData();
    }, [url]);

    return { data, error, isLoading };
}