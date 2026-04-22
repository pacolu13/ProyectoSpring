export interface ProductListingDTO {
  id: number;
  price: number;
  productName: string;
  creationDate: Date;
  condition: ConditionDTO;
  seller: SellerDTO;
}

export interface ProductListingDetailsDTO {
  id: number;
  price: number;
  productName: string;
  description: string;
  condition: ConditionDTO;
  seller: SellerDTO;
  creationDate: Date;
}

export interface SellerDTO {
  username: string;
  totalSales: number;       // ventas totales del vendedor
  rating: number;      // 0–5
  location: string;          // ciudad/provincia

}

export type ConditionDTO = "NEW" | "USED" | null;