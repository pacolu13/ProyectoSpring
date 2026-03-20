
import './Cart.css'
import { Grid, Button, ProductCart } from "../../components";
import { useFetch } from '../../hooks/useFetch';
import type { CartDTO } from '../../interfaces';

export const Cart = () => {
    const { data, isLoading, error } = useFetch<CartDTO>("/api/v1/cart");

    if (data == null) return null;

    return <>
        <Grid columns={3}>
            {data.products.map((item) => (
                <ProductCart
                    key={item.id}
                    name={item.name}
                    productListingId={item.productListingId}
                    quantity={item.quantity} id={0}
                />
            ))}
        </Grid>
        <Button label="Confirmar Compra" parentMethod={() => null}></Button>
    </>
}