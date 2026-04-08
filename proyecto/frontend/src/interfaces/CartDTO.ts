export interface CartProduct {
  id: number;
  name: string;
  productListingId: number;
  unitPrice: number;
  quantity: number;
  subtotal: number;
  onIncrease: (productListingId: number, unitPrice: number) => void;
  onDecrease: (productListingId: number, unitPrice: number) => void;
}

export interface CartDTO {
  id: number;
  products: CartProduct[];
  total: number;
}