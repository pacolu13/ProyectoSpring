import { useState } from "react";
import { useToast, EditListingModal } from "../../../components";
import type { ProductSellDTO, CreateProductSellDTO, CategoryDTO } from "../../../interfaces";
import { usePut, useFetch, useDelete } from "../../../hooks";

type Props = {
    listing: ProductSellDTO;
};

export const ListingRow = ({ listing }: Props) => {
    const showToast = useToast();
    const [isModalOpen, setIsModalOpen] = useState(false);
    const { put } = usePut<ProductSellDTO>(`/api/v1/product-listings/${listing.id}`);
    const { constDelete } = useDelete<void>(`/api/v1/product-listings/${listing.id}`);
    const { data: categories } = useFetch<CategoryDTO[]>("/api/v1/categories", true);

    const onUpdate = async (data: CreateProductSellDTO) => {
        try {
            await put(data);
            showToast("confirm", "Publicación actualizada con éxito");
            setIsModalOpen(false);
        } catch {
            showToast("error", "Error al actualizar la publicación");
        }
    };

    const onDelete = async () => {
        try {
            await constDelete();
            showToast("confirm", "Publicación eliminada con éxito");
        } catch {
            showToast("error", "Error al eliminar la publicación");
        }
    };

    return (
        <>
            <tr>
                <td>{listing.product.name}</td>
                <td>${listing.price.toFixed(2)}</td>
                <td>{listing.stock}</td>
                <td className="ml-actions">
                    <button onClick={() => setIsModalOpen(true)} className="ml-btn ml-btn--edit">
                        Editar
                    </button>
                    <button onClick={onDelete} className="ml-btn ml-btn--delete">
                        Eliminar
                    </button>
                </td>
            </tr>

            {isModalOpen && (
                <EditListingModal
                    listing={listing}
                    categories={categories}
                    onClose={() => setIsModalOpen(false)}
                    onSubmit={onUpdate}
                />
            )}
        </>
    );
};