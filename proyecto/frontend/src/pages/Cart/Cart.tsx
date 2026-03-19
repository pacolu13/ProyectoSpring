
import './Cart.css'
import { useCart } from "../../hooks/useCart";
import { Grid, Button, ProductCart } from "../../components";

export const Cart = () => {
    const { cart, loading, error } = useCart();

    if (cart == null) return null;

    return <>
        <Grid columns={3}>
            {cart.products.map((item, i) => (
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