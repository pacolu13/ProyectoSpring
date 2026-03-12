import { useState, useMemo } from "react";
import { useParams } from "react-router-dom";
import { useFetch } from "../../hooks";
import { ProductCard } from "../../components/ProductCard/ProductCard";
import './ProductListing.css';

interface ProductListingList {
  productsList: ProductListing[];
}

interface ProductListing {
  id: number;
  price: number;
  productName: string;
  sellerName: string;
}

type SortOption = "price-asc" | "price-desc" | "none";

export const ProductListing = () => {
  const { idProduct } = useParams();
  const { data, error, isLoading } = useFetch<ProductListingList>(
    `/api/v1/product-listing/${idProduct}`
  );

  const [sort, setSort] = useState<SortOption>("none");
  const [maxPrice, setMaxPrice] = useState<number | "">("");

  const filtered = useMemo(() => {
    if (!data?.productsList) return [];
    let list = [...data.productsList];
    if (maxPrice !== "") list = list.filter((p) => p.price <= maxPrice);
    if (sort === "price-asc") list.sort((a, b) => a.price - b.price);
    if (sort === "price-desc") list.sort((a, b) => b.price - a.price);
    return list;
  }, [data, sort, maxPrice]);

  const handleBuy = (id: number) => {
    // TODO: lógica de compra
    console.log("Comprar producto listing id:", id);
  };

  if (idProduct == null) return null;

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
            value={maxPrice}
            onChange={(e) =>
              setMaxPrice(e.target.value === "" ? "" : Number(e.target.value))
            }
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
                className={`listing-filters__sort-btn${sort === opt.value ? " active" : ""}`}
                onClick={() => setSort(opt.value)}
              >
                {opt.label}
              </button>
            ))}
          </div>
        </div>

        <button
          className="listing-filters__clear"
          onClick={() => { setSort("none"); setMaxPrice(""); }}
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
        {!isLoading && !error && filtered.length === 0 && (
          <p className="listing-state">No hay publicaciones disponibles.</p>
        )}
        {filtered.map((item, i) => (
          <div
            key={item.id}
            className="listing-item"
            style={{ animationDelay: `${i * 60}ms` }}
          >
            <ProductCard
              id={item.id}
              price={item.price}
              productName={item.productName}
              sellerName={item.sellerName}
              onBuy={handleBuy}
            />
          </div>
        ))}
      </main>
    </div>
  );
};