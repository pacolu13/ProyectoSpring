export interface CartProduct {
  id: number;
  name: string;
  productListingId: number;
  quantity: number;
}

export interface Cart {
  id: number;
  products: CartProduct[];
  total: number;
}