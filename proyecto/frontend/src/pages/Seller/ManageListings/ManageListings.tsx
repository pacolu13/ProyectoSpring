import { useFetch } from "../../../hooks";
import { ListingRow, useToast } from "../../../components";
import type { ProductSellDTO } from "../../../interfaces";
import "./ManageListings.css";

export const ManageListings = () => {
    const { data, error } = useFetch<ProductSellDTO[]>("/api/v1/product-listings/seller", true);

    if (error) {
        return <div className="ml-error">Error al cargar las publicaciones</div>;
    }

    return (
        <div className="ml-root">
            <h2 className="ml-title">Mis Publicaciones</h2>

            <table className="ml-table">
                <thead>
                    <tr>
                        <th>Producto</th>
                        <th>Precio</th>
                        <th>Estado</th>
                        <th>Categoría</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    {data?.map((listing) => (
                        <ListingRow key={listing.id} listing={listing} />
                    ))}
                </tbody>
            </table>
        </div>
    );
};