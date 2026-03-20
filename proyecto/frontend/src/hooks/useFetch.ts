import { useState, useEffect } from "react";
import { useAuth } from "../context/AuthContext";
import api from "../services/api";

type Data<T> = T | null;
type Error = string | null;

interface Params<T> {
  data: Data<T>;
  error: Error;
  isLoading: boolean;
}


export const useFetch = <T>(url: string): Params<T> => {
  const { token } = useAuth();
  const [data, setData] = useState<Data<T>>(null);
  const [error, setError] = useState<Error>(null);
  const [isLoading, setIsLoading] = useState<boolean>(true);

  useEffect(() => {
    if (!token) {          // <- usás el token del contexto, no localStorage
      setData(null);
      setError("No autenticado");
      setIsLoading(false);
      return;
    }

    setIsLoading(true);
    api.get(url)
      .then(setData)
      .catch((e) => setError(e.message))
      .finally(() => setIsLoading(false));
  }, [token, url]);             // <- se reactiva cuando el contexto cambia

  return { data, error, isLoading };
}