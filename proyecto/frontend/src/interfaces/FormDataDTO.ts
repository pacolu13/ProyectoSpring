export interface FormDataDTO {
    title: string;
    description: string;
    price: string;
    stock: string;
    state: ProductStateDTO;
    productName: string;
    productDescription: string;
    brand: string;
    category: string;
    customCategory: string;
    image: string;
}

export type ProductStateDTO = "NEW" | "USED";