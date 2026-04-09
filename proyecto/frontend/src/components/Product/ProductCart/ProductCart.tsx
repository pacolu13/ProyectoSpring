import "./ProductCart.css";
import type { CartProduct } from "../../../interfaces";
import { useState } from "react";
import { formatPrice } from "../../../utils/FormatPrice";
import { useDelete } from "../../../hooks";
import { useToast } from "../..";

export const ProductCart = ({ id, name, quantity, subtotal, productListingId, unitPrice, onIncrease, onDecrease }: CartProduct) => {
  const [thisQuantity, setQuantity] = useState(quantity);
  const [thisSubtotal, setSubtotal] = useState(subtotal);

  const { constDelete } = useDelete<void>(`/api/v1/carts/${productListingId}`);
  const showToast = useToast();

  const handleIncrease = () => {
    setQuantity(q => q + 1);
    onIncrease(productListingId, unitPrice);

    setSubtotal(q => q + unitPrice);

  };

  const handleDecrease = () => {
    if (thisQuantity <= 1) return;
    setQuantity(q => q - 1);
    onDecrease(productListingId, unitPrice);

    setSubtotal(q => q - unitPrice);
  };

  const onDelete = async () => {
    try {
      await constDelete();
      showToast('confirm', 'Compra confirmada exitosamente');
    }
    catch (error) {
      showToast('error', 'Error al eliminar el producto del carrito');
    }
  };


  return (
    <article className="pc">
      <div className="pc__info">
        <h3 className="pc__name">{name}</h3>
        <div className="card__quantity">
          <button onClick={handleDecrease}>−</button>
          <span>{thisQuantity}</span>
          <button onClick={handleIncrease}>+</button>
        </div>
      </div>
      <div className="pc__controls">
        <div className="pc__subtotal">
          <span className="pc__subtotal-label">Subtotal</span>
          <div className="pc__subtotal-value">{formatPrice(thisSubtotal)}</div>
        </div>
      </div>
      <div className="pc__button-delete">
        <button onClick={onDelete}>Eliminar</button>
      </div>
    </article>
  );
};
