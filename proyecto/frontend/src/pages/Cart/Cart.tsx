
import { useEffect, useState } from "react";
import { Button, ProductCart, useToast } from "../../components";
import { useFetch, usePost, usePut } from '../../hooks';
import type { CartDTO, OrderDTO } from '../../interfaces';
import './Cart.css'
import { formatPrice } from "../../utils/FormatPrice";

export const Cart = () => {
    const showToast = useToast();
    const { data } = useFetch<CartDTO>("/api/v1/carts", true);
    const { put, putError } = usePut<CartDTO>("/api/v1/carts");
    const { post, addError } = usePost<OrderDTO>("/api/v1/orders/submit");
    const [thisTotal, setTotal] = useState<number>(0);

    
    useEffect(() => {
        if (data?.total != null) setTotal(data.total);
    }, [data]);

    if (data == null) return null;

    const handleIncrease = async (productListingId: number, unitPrice: number): Promise<void> => {
        await put({ productListingId, quantity: 1 });
        if (putError) {
            showToast('error', 'Error al actualizar el carrito');
        } else {
            setTotal(t => t + unitPrice);
        }
    };

    const handleDecrease = async (productListingId: number, unitPrice: number): Promise<void> => {
        await put({ productListingId, quantity: -1 });
        if (putError) {
            showToast('error', 'Error al actualizar el carrito');
        } else {
            setTotal(t => t - unitPrice);
        }
    };

    const handleBuy = async (): Promise<void> => {
        await post(null);
        if (addError) {
            showToast('error', 'Error al realizar la compra');
        } else {
            showToast('confirm', 'Compra realizada');
        }
    };

    return (
        <div className='body_cart'>
            <div className='cart'>
                <div className='cart__tittle'>
                    <span>CARRITO</span>
                    <span>{data.cartProductsList.length} items</span>
                </div>
                {data.cartProductsList.map((item) => (
                    <ProductCart
                        key={item.id}
                        name={item.name}
                        productListingId={item.productListingId}
                        unitPrice={item.unitPrice}
                        quantity={item.quantity}
                        id={item.id}
                        subtotal={item.subtotal}
                        onIncrease={handleIncrease}
                        onDecrease={handleDecrease}
                    />
                ))}
                <div className='cart__total'>
                    <span>Total</span>
                    <span>{formatPrice(thisTotal)}</span>
                </div>
                <Button label="Confirmar Compra" parentMethod={handleBuy} />
            </div>
        </div>
    );
};