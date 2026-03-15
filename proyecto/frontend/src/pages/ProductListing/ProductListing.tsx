import { useParams } from "react-router-dom";
import { useFetch } from "../../hooks";
import { ProductListingCard, ListingFilters } from "../../components/index";
import type { ProductListingDTO } from "../../interfaces/ProductListingDTO";
import './ProductListing.css';

export const ProductListing = () => {
  const { idProduct } = useParams();
  const { data, error, isLoading } = useFetch<ProductListingDTO[]>(
    `/api/v1/product-listings/${idProduct}`
  );
  //Modificar, actualmente no devuelve nada
  if (idProduct == null || data == null) return null;

  const handleBuy = (id: number) => {
    // TODO: lógica de compra
    console.log("Comprar producto listing id:", id);
  };

  return (
    <div className="listing-root">
      {/* ── Sidebar filtros ── */}
      <ListingFilters></ListingFilters>
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
        {data.length == 0 && (
          <p className="listing-state">No hay publicaciones disponibles.</p>
        )}
        {data.map((item, i) => (
          <div
            key={item.id}
            className="listing-item"
            style={{ animationDelay: `${i * 60}ms` }}
          >
            <ProductListingCard
              id={item.id}
              price={item.price}
              productName={item.productName}
              sellerName={item.sellerName}
              onBuy={handleBuy}
              creationDate={item.creationDate}
              sellerSales={0}
              sellerRating={0}
              condition={"new"}
              location={""}
            />
          </div>
        ))}
      </main>
    </div>
  );
};