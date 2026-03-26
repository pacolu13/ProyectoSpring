export interface OrderDTO {
    clientUsername: string;
    orderDate: string;
    orderDetailsList: OrderDetailsDTO[],
    totalBalance: number;
}

export interface OrderDetailsDTO {
    id: number;
    productName: string;
    quantity: number;
    unitPrice: number;
}