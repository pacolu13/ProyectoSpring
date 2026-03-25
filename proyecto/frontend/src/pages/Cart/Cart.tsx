
import './Cart.css'
import { Button, ProductCart } from "../../components";
import { useFetch } from '../../hooks/useFetch';
import type { CartDTO } from '../../interfaces';

export const Cart = () => {
    const { data, isLoading, error } = useFetch<CartDTO>("/api/v1/carts", true);
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
                        quantity={item.quantity} id={0}
                        subtotal={item.subtotal}
                    />
                ))}
                <div className='cart__total'>
                    <span>Total</span>
                    <span>{data.total}</span>
                </div>
                <Button label="Confirmar Compra" parentMethod={() => null}></Button>
            </div>
        </div>
    </>
}