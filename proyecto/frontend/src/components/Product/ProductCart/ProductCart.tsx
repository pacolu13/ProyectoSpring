import "./ProductCart.css";
import type { CartProduct } from "../../../interfaces";

export const ProductCart = ({ id, name, quantity, subtotal, productListingId, onIncrease, onDecrease }: CartProduct) => {
  return (
    <article className="pc">
      <div className="pc__info">
        <h3 className="pc__name">{name}</h3>
        <div className="card__quantity">
          <button onClick={() => onDecrease(productListingId)}>−</button>
          <span>{quantity}</span>
          <button onClick={() => onIncrease(productListingId)}>+</button>
        </div>
      </div>
      <div className="pc__controls">
        <div className="pc__subtotal">
          <span className="pc__subtotal-label">Subtotal</span>
          <div className="pc__subtotal-value">{subtotal}</div>
        </div>
      </div>
    </article>
  );
};
