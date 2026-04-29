export interface ProductListingDTO {
  id: number;
  price: number;
  productName: string;
  description: string;
  creationDate: Date;
  state: ConditionDTO;
  seller: SellerDTO;
}

export interface ProductListingDetailsDTO {
  id: number;
  price: number;
  productName: string;
  description: string;
  state: ConditionDTO;
  seller: SellerDTO;
  creationDate: Date;
}

export interface SellerDTO {
  username: string;
  totalSales: number;
  rating: number;
  location: string;

}

export type ConditionDTO = "NEW" | "USED" | null;