import "./ProductCart.css";
import type { CartProduct } from "../../../interfaces";

export const ProductCart = ({ id, name, quantity }: CartProduct) => {
  return (
    <article className="pc">
      <div className="pc__info">
        <h3 className="pc__name">{name}</h3>
        <p>{id} - {quantity}</p>
      </div>

      <div className="pc__controls">
        <div className="pc__subtotal">
          <span className="pc__subtotal-label">Subtotal</span>
        </div>
      </div>
    </article>
  );
};
