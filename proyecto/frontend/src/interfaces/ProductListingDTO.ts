export interface ProductListingDTO {
  id: number;
  price: number;
  productName: string;
  creationDate: Date;
  condition: "new" | "used";
  seller: SellerDTO;
}

export interface SellerDTO {
  username: string;
  totalSales: number;       // ventas totales del vendedor
  rating: number;      // 0–5
  location: string;          // ciudad/provincia

}