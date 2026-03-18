// hooks/useCart.ts
import { useState, useEffect } from "react";
import api from "../services/api";
import type { Cart } from "../interfaces/Cart";

export const useCart = () => {
  const [cart, setCart] = useState<Cart | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    api("/api/v1/cart")
      .then(setCart)
      .catch((e) => setError(e.message))
      .finally(() => setLoading(false));
  }, []);

  return { cart, loading, error };
};