import type { ProductDTO } from "./ProductDTO";

export interface CreateProductSellDTO {
    title: string;
    description: string;
    price: number;
    state: string;
    stock: number;
    product: ProductDTO;
}

export type ProductSellDTO = CreateProductSellDTO & {
    id: number;
};