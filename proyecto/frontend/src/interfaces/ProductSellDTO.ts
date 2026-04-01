import type { ProductDTO } from "./ProductDTO";

export interface CreateProductSellDTO {
    title: string;
    price: number;
    state: string;
    quantity: number;
    product: ProductDTO;
}

export type ProductSellDTO = CreateProductSellDTO & {
    id: number;
};