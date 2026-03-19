import { FormatDate } from "../../../utils/FormatDate";
import { Button } from "../../Button/Button";
import "./ProductListingCard.css";

interface ProductCardProps {
  id: number;
  price: number;
  productName: string;
  sellerName: string;
  sellerSales: number;       // ventas totales del vendedor
  sellerRating: number;      // 0–5
  condition: "new" | "used"; // condición del producto
  location: string;          // ciudad/provincia
  freeShipping?: boolean;
  creationDate: string;
  onBuy: (id: number) => void;
}

const StarRating = ({ rating }: { rating: number }) => {
  return (
    <span className="plc__stars" aria-label={`${rating} de 5 estrellas`}>
      {[1, 2, 3, 4, 5].map((s) => (
        <svg
          key={s}
          className={`plc__star ${s <= Math.round(rating) ? "plc__star--filled" : ""}`}
          viewBox="0 0 12 12"
          fill="none"
        >
          <path
            d="M6 1l1.236 2.506L10 3.927l-2 1.947.472 2.752L6 7.25 3.528 8.626 4 5.874 2 3.927l2.764-.421L6 1z"
            fill="currentColor"
          />
        </svg>
      ))}
    </span>
  );
};

export const ProductListingCard = ({
  id,
  price,
  productName,
  sellerName,
  sellerSales,
  sellerRating,
  condition,
  location,
  freeShipping,
  creationDate,
  onBuy,
}: ProductCardProps) => {
  return (
    <article className="plc">
      {/* Badge condición */}
      <span className={`plc__badge plc__badge--${condition}`}>
        {condition === "new" ? "Nuevo" : "Usado"}
      </span>

      <div className="plc__body">
        {/* Nombre del producto */}
        <h2 className="plc__name">{productName}</h2>

        {/* Precio */}
        <div className="plc__price-row">
          <span className="plc__currency">$</span>
          <span className="plc__price">{price.toLocaleString("es-AR")}</span>
        </div>

        {/* Envío gratis */}
        {freeShipping && (
          <span className="plc__shipping">
            <svg viewBox="0 0 16 12" fill="none" className="plc__shipping-icon">
              <path d="M1 6h9M10 1h3l2 4v4h-5V1z" stroke="currentColor" strokeWidth="1.2" strokeLinecap="round" strokeLinejoin="round" />
              <circle cx="3.5" cy="10.5" r="1.5" fill="currentColor" />
              <circle cx="11.5" cy="10.5" r="1.5" fill="currentColor" />
            </svg>
            Envío gratis
          </span>
        )}
      </div>

      <div className="plc__divider" />

      {/* Vendedor */}
      <div className="plc__seller">
        <div className="plc__seller-avatar">
          {sellerName.charAt(0).toUpperCase()}
        </div>
        <div className="plc__seller-info">
          <span className="plc__seller-name">{sellerName}</span>
          <div className="plc__seller-meta">
            <StarRating rating={sellerRating} />
            <span className="plc__seller-sales">{sellerSales.toLocaleString("es-AR")} ventas</span>
          </div>
        </div>
      </div>

      {/* Footer */}
      <div className="plc__footer">
        <div className="plc__meta">
          <span className="plc__location">
            <svg viewBox="0 0 10 13" fill="none" className="plc__meta-icon">
              <path d="M5 1C2.79 1 1 2.79 1 5c0 3 4 7 4 7s4-4 4-7c0-2.21-1.79-4-4-4zm0 5.5A1.5 1.5 0 1 1 5 3.5a1.5 1.5 0 0 1 0 3z" fill="currentColor" />
            </svg>
            {location}
          </span>
          <span className="plc__date">{FormatDate(creationDate)}</span>
        </div>
        <Button label="Comprar" parentMethod={() => onBuy(id)} />
      </div>
    </article>
  );
};
