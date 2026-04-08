
import { useEffect, useState } from "react";
import { Button, ProductCart, useToast } from "../../components";
import { useFetch, usePost, usePut } from '../../hooks';
import type { CartDTO, OrderDTO } from '../../interfaces';
import './Cart.css'
import { formatPrice } from "../../utils/FormatPrice";

export const Cart = () => {
    const showToast = useToast();
    const { data } = useFetch<CartDTO>("/api/v1/carts", true);
    const { put } = usePut<CartDTO>("/api/v1/carts");
    const { post } = usePost<OrderDTO>("/api/v1/orders/submit");
    const [thisTotal, setTotal] = useState<number>(0);

    useEffect(() => {
        if (data?.total != null) setTotal(data.total);
    }, [data]);

    if (data == null) return null;

    const handleIncrease = async (productListingId: number, unitPrice: number): Promise<void> => {
        try {
            await put({ productListingId, quantity: 1 });
            setTotal(t => t + unitPrice);
        }
        catch (error) {
            showToast('error', 'Error al actualizar el carrito');
        }
    };

    const handleDecrease = async (productListingId: number, unitPrice: number): Promise<void> => {
        try {
            await put({ productListingId, quantity: -1 });
            setTotal(t => t - unitPrice);
        } catch (error) {
            showToast('error', 'Error al actualizar el carrito');
        }
    };

    const handleBuy = async (): Promise<void> => {
        try {
            await post(null);
            showToast('confirm', 'Compra confirmada exitosamente');
        }
        catch (error) {
            showToast('error', 'Error al confirmar la compra');
        }
    };

    return (
        <div className='body_cart'>
            <div className='cart'>
                <div className='cart__tittle'>
                    <span>CARRITO</span>
                    <span>{data.products?.length ?? 0} items</span>
                </div>
                {(data.products ?? []).map((item) => (
                    console.log(item),
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