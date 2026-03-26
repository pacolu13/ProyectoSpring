
import { Button, ProductCart, useToast } from "../../components";
import { useFetch, usePost, usePut } from '../../hooks';
import type { CartDTO, OrderDTO } from '../../interfaces';
import './Cart.css'

export const Cart = () => {
    const showToast = useToast();
    const { data } = useFetch<CartDTO>("/api/v1/carts", true);
    const { put, putError } = usePut<CartDTO>("/api/v1/carts");
    const { post, addError } = usePost<OrderDTO>("/api/v1/orders/submit");

    const handleIncrease = async (productListingId: number): Promise<void> => {
        await put({ productListingId, quantity: 1 });
        if (putError) showToast('error', 'Error al actualizar el carrito');
    };

    const handleDecrease = async (productListingId: number): Promise<void> => {
        await put({ productListingId, quantity: -1 });
        if (putError) showToast('error', 'Error al actualizar el carrito');
    };


    const handleBuy = async (): Promise<void> => {
        await post(null);

        if (addError) {
            showToast('error', 'Error al realizar la compra');
        } else {
            showToast('confirm', 'Compra realizada');
        }
    };

    if (data == null) return null;


    return <>
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
                        quantity={item.quantity}
                        id={item.id}
                        subtotal={item.subtotal}
                        onIncrease={handleIncrease}
                        onDecrease={handleDecrease}
                    />
                ))}
                <div className='cart__total'>
                    <span>Total</span>
                    <span>{data.total}</span>
                </div>
                <Button label="Confirmar Compra" parentMethod={handleBuy}></Button>
            </div>
        </div>
    </>
}