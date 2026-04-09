import type { ProductDTO } from "./ProductDTO";

export interface CreateProductSellDTO {
    title: string;
    description: string;
    price: number;
    state: ProductStateDTO;
    stock: number;
    product: ProductDTO;
}

export type ProductSellDTO = CreateProductSellDTO & {
    id: number;
};

export type ProductStateDTO = "NEW" | "USED" | "DAMAGED";