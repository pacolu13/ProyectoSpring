import { useParams } from "react-router-dom";
import { useFetch, usePost } from "../../hooks/index";
import { ProductListingCard, ListingFilters, useToast } from "../../components/index";
import type { ProductListingDTO, CartDTO } from "../../interfaces/index";
import './ProductListing.css';

export const ProductListing = () => {
  const { idProduct } = useParams();
  const showToast = useToast();
  const { data, error, isLoading } = useFetch<ProductListingDTO[]>(
    `/api/v1/product-listings/${idProduct}`, false
  );

  const { post, addError } = usePost<CartDTO>("/api/v1/carts");

  //Modificar, actualmente no devuelve nada
  if (idProduct == null || data == null) return null;

  const handleBuy = async (id: number): Promise<void> => {
    await post({ productListingId: id, quantity: 1 }); // -----> Modificar la cantidad para que el usuario ponga varios

    if (addError) {
      showToast('error', 'Error al añadir al carrito');
    } else {
      showToast('confirm', 'Producto añadido al carrito');
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