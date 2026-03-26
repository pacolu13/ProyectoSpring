export interface CartProduct {
  id: number;
  name: string;
  productListingId: number;
  quantity: number;
  subtotal: number;
  onIncrease: (productListingId: number) => void;
  onDecrease: (productListingId: number) => void;
}

export interface CartDTO {
  id: number;
  cartProductsList: CartProduct[];
  total: number;
}