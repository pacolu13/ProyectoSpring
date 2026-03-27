import type { ProductDTO } from "./ProductDTO";

export interface ProductSellDTO {
    title: String;
    price: number;
    quantity: number;
    state: string;
    stock: number;
    product: ProductDTO;
}