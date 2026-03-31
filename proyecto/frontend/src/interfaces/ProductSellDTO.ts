import type { ProductDTO } from "./ProductDTO";

export interface ProductSellDTO {
    title: String;
    price: number;
    state: string;
    quantity: number;
    product: ProductDTO;
}