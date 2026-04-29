import { useState } from "react";
import type { ProductListingDetailsDTO } from "../../../interfaces";
import { FormatDate } from "../../../utils/FormatDate";
import { ConditionBadge } from "../../ConditionBagde/ConditionBagde";
import { StarRating } from "../../StarRating/StarRating";
import "./ProductListingCard.css";

interface Props {
  Product: ProductListingDetailsDTO,
  onBuy: (id: number, quantity: number) => Promise<void>
}

export const ProductListingCard = ({ Product, onBuy }: Props) => {
  const [quantity, setQuantity] = useState(1);

  console.log(Product);
  return (
    <article className="plc">
      <ConditionBadge condition={Product.state} />

      <div className="plc__body">
        <h2 className="plc__name">{Product.productName}</h2>
        <div className="plc__price-row">
          <span className="plc__currency">$</span>
          <span className="plc__price">{Product.price.toLocaleString("es-AR")}</span>
        </div>
      </div>

      <div className="plc__divider" />

      <div className="plc__seller">
        <div className="plc__seller-avatar">
          {Product.seller.username.charAt(0).toUpperCase()}
        </div>
        <div className="plc__seller-info">
          <span className="plc__seller-name">{Product.seller.username}</span>
          <div className="plc__seller-meta">
            <StarRating rating={Product.seller.rating} />
            <span className="plc__seller-sales">
              {Product.seller.totalSales.toLocaleString("es-AR")} ventas
            </span>
          </div>
        </div>
      </div>

      <div className="plc__footer">
        <div className="plc__meta">
          <span className="plc__location">
            <svg viewBox="0 0 10 13" fill="none" className="plc__meta-icon">
              <path d="M5 1C2.79 1 1 2.79 1 5c0 3 4 7 4 7s4-4 4-7c0-2.21-1.79-4-4-4zm0 5.5A1.5 1.5 0 1 1 5 3.5a1.5 1.5 0 0 1 0 3z" fill="currentColor" />
            </svg>
            {Product.seller.location}
          </span>
          <span className="plc__date">{FormatDate(Product.creationDate)}</span>
        </div>

        <div className="card__quantity">
          <button onClick={() => setQuantity(q => Math.max(1, q - 1))}>-</button>
          <span>{quantity}</span>
          <button onClick={() => setQuantity(q => q + 1)}>+</button>
        </div>

        <button
          className="plc__buy-btn"
          onClick={() => onBuy(Product.id, quantity)}
        >
          Añadir al carrito
        </button>
      </div>
    </article>
  );
};