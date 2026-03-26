export interface CartProduct {
  id: number;
  name: string;
  productListingId: number;
  quantity: number;
  subtotal: number;
}

export interface CartDTO {
  id: number;
  cartProductsList: CartProduct[];
  total: number;
}