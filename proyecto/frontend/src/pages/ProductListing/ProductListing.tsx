import { useState, useMemo } from "react";
import { useParams } from "react-router-dom";
import { useFetch } from "../../hooks";
import { ProductCard } from "../../components/ProductCard/ProductCard";
import './ProductListing.css';


interface ProductListing {
  id: number;
  price: number;
  nameProduct: string;
  nameSeller: string;
}

type SortOption = "price-asc" | "price-desc" | "none";

export const ProductListing = () => {
  const { idProduct } = useParams();
  const { data, error, isLoading } = useFetch<ProductListing[]>(
    `/api/v1/product-listings/${idProduct}`
  );

  const handleBuy = (id: number) => {
    // TODO: lógica de compra
    console.log("Comprar producto listing id:", id);
  };

  if (idProduct == null || data == null) return null;

  return (
    <div className="listing-root">
      {/* ── Sidebar filtros ── */}
      <aside className="listing-filters">
        <p className="listing-filters__title">Filtros</p>

        <div className="listing-filters__group">
          <label className="listing-filters__label">Precio máximo</label>
          <input
            className="listing-filters__input"
            type="number"
            placeholder="$ sin límite"
            value={0}
          />
        </div>

        <div className="listing-filters__group">
          <label className="listing-filters__label">Ordenar por</label>
          <div className="listing-filters__sort">
            {(
              [
                { value: "none", label: "Relevancia" },
                { value: "price-asc", label: "Menor precio" },
                { value: "price-desc", label: "Mayor precio" },
              ] as { value: SortOption; label: string }[]
            ).map((opt) => (
              <button
                key={opt.value}
                className={`listing-filters__sort-btn${"active"}`}
              >
                {opt.label}
              </button>
            ))}
          </div>
        </div>

        <button
          className="listing-filters__clear"
        >
          Limpiar filtros
        </button>
      </aside>

      {/* ── Lista ── */}
      <main className="listing-main">
        {isLoading && (
          <p className="listing-state">Cargando publicaciones…</p>
        )}
        {error && (
          <p className="listing-state listing-state--error">
            No se pudieron cargar las publicaciones.
          </p>
        )}
        {!isLoading && !error && (
          <p className="listing-state">No hay publicaciones disponibles.</p>
        )}
        {data.map((item, i) => (
          <div
            key={item.id}
            className="listing-item"
            style={{ animationDelay: `${i * 60}ms` }}
          >
            <ProductCard
              id={item.id}
              price={item.price}
              productName={item.nameProduct}
              sellerName={item.nameSeller}
              onBuy={handleBuy}
            />
          </div>
        ))}
      </main>
    </div>
  );
};