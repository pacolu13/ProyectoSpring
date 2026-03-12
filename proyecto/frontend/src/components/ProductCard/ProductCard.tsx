import './ProductCard.css';

interface ProductCardProps {
  id: number;
  price: number;
  productName: string;
  sellerName: string;
  onBuy: (id: number) => void;
}

export const ProductCard = ({ id, price, productName, sellerName, onBuy }: ProductCardProps) => {
  return (
    <article className="product-card">
      <div className="product-card__body">
        <p className="product-card__seller">{sellerName}</p>
        <h2 className="product-card__name">{productName}</h2>
        <p className="product-card__price">
          <span className="product-card__currency">$</span>
          {price.toLocaleString("es-AR")}
        </p>
      </div>
      <button className="product-card__btn" onClick={() => onBuy(id)}>
        Comprar
      </button>
    </article>
  );
};
