import "./ProductCart.css";
import type { CartProduct } from "../../../interfaces";
import { useState } from "react";
import { formatPrice } from "../../../utils/FormatPrice";
import { HandleQuantity } from "../../HandleQuantity/HandleQuantity";

export const ProductCart = ({ id, name, quantity, subtotal, productListingId, unitPrice, onIncrease, onDecrease, onDelete }: CartProduct) => {
  const [thisQuantity, setQuantity] = useState(quantity);
  const [thisSubtotal, setSubtotal] = useState(subtotal);

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

  return (
    <article className="pc">
      <div className="pc__info">
        <h3 className="pc__name">{name}</h3>
        <HandleQuantity
          quantity={thisQuantity}
          onIncrease={handleIncrease}
          onDecrease={handleDecrease}
        />
      </div>
      <div className="pc__controls">
        <div className="pc__subtotal">
          <span className="pc__subtotal-label">Subtotal</span>
          <div className="pc__subtotal-value">{formatPrice(thisSubtotal)}</div>
        </div>
      </div>
      <div className="pc__button-delete">
        <button onClick={() => onDelete(productListingId)}>Eliminar</button>
      </div>
    </article>
  );
};
