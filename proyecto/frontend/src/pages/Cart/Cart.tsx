
import { useEffect, useState } from "react";
import { Button, ProductCart, useToast } from "../../components";
import { useDelete, useFetch, usePost, usePut } from '../../hooks';
import type { CartDTO, OrderDTO } from '../../interfaces';
import './Cart.css'
import { formatPrice } from "../../utils/FormatPrice";
import { cartRoutes } from "../../api/routes";

export const Cart = () => {
    const showToast = useToast();
    const { data } = useFetch<CartDTO>(cartRoutes.get, true);
    const { put } = usePut<CartDTO>(cartRoutes.update);
    const { post } = usePost<OrderDTO>(cartRoutes.submit);
    const [thisTotal, setTotal] = useState<number>(0);
    const { constDelete } = useDelete<void>('');

    useEffect(() => {
        if (data?.total != null) setTotal(data.total);
    }, [data]);

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

    const handleDelete = async (productListingId: number): Promise<void> => {
        try {
            await constDelete(cartRoutes.delete(productListingId));
            showToast('confirm', 'Producto eliminado: Recargue el navegador');
        }
        catch (error) {
            showToast('error', 'Error al eliminar el producto del carrito');
        }
    };

    if (data == null) return <div className='body_cart'>
        <div className='cart'>
            <div className='cart__tittle'>
                <span>CARRITO</span>
                <span>0 items</span>
            </div>
            <div className='cart__total'>
                <span>Total</span>
                <span>{formatPrice(0)}</span>
            </div>
        </div>
    </div>;

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
                        onDelete={handleDelete}
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