import { useParams } from "react-router-dom";
import { useFetch } from "../../hooks/useFetch";
import { ProductListingCard, ListingFilters } from "../../components/index";
import type { ProductListingDTO } from "../../interfaces/ProductListingDTO";
import './ProductListing.css';
import { usePost } from "../../hooks/usePost";
import type { CartDTO } from "../../interfaces";

export const ProductListing = () => {
  const { idProduct } = useParams();
  const { data, error, isLoading } = useFetch<ProductListingDTO[]>(
    `/api/v1/product-listings/${idProduct}`, false
  );

  const { post, loading } = usePost<CartDTO>("/api/v1/carts");

  //Modificar, actualmente no devuelve nada
  if (idProduct == null || data == null) return null;

  const handleBuy = async (id: number): Promise<void> => {
    console.log(id);
    const result = await post({ productListingId: id, quantity: 1 });
    if (result) {
      console.log("Producto añadido -> Cambiarlo por popup");
    }
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
              onBuy={handleBuy}
              creationDate={item.creationDate}
              seller={item.seller}
              condition={item.condition}
            />
          </div>
        ))}
      </main>
    </div>
  );
};