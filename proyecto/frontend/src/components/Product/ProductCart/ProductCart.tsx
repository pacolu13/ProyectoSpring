import "./ProductCart.css";

interface ProductCartProps {
  id: number;
  productListingId: number;
  name: string;
  quantity: number;
  onRemove: (id: number) => void;
  onQuantityChange: (id: number, quantity: number) => void;
}

export const ProductCart = ({
  id,
  name,
  quantity,
  onRemove,
  onQuantityChange,
}: ProductCartProps) => {
  return (
    <article className="pc">
      {/* Lado izquierdo — info */}
      <div className="pc__info">
        <h3 className="pc__name">{name}</h3>

      </div>

      {/* Lado derecho — controles */}
      <div className="pc__controls">
        {/* Botón eliminar */}
        <button className="pc__remove" onClick={() => onRemove(id)} aria-label="Eliminar">
          <svg viewBox="0 0 14 14" fill="none">
            <path d="M2 4h10M5 4V2h4v2M6 7v4M8 7v4M3 4l.5 8h7l.5-8" stroke="currentColor" strokeWidth="1.2" strokeLinecap="round" strokeLinejoin="round" />
          </svg>
        </button>

        {/* Cantidad */}
        <div className="pc__qty">
          <button
            className="pc__qty-btn"
            onClick={() => onQuantityChange(id, quantity - 1)}
            disabled={quantity <= 1}
          >
            −
          </button>
          <span className="pc__qty-value">{quantity}</span>
          <button
            className="pc__qty-btn"
            onClick={() => onQuantityChange(id, quantity + 1)}
          >
            +
          </button>
        </div>

        {/* Subtotal */}
        <div className="pc__subtotal">
          <span className="pc__subtotal-label">Subtotal</span>
        </div>
      </div>
    </article>
  );
};
