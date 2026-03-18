
import { Grid } from "../../components/Grid/Grid"
import './Cart.css'
import { Button } from "../../components";
import { useCart } from "../../hooks/useCart";
import { ProductCart } from "../../components/Product/ProductCart/ProductCart";

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
                    quantity={item.quantity} id={0} onRemove={function (id: number): void {
                        throw new Error("Function not implemented.");
                    }} onQuantityChange={function (id: number, quantity: number): void {
                        throw new Error("Function not implemented.");
                    }}
                />
            ))};
        </Grid>
        <Button label="Confirmar Compra" parentMethod={() => null}></Button>
    </>
}