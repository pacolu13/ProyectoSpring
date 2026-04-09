import { useState } from "react";
import { useToast, EditListingModal } from "../../../components";
import type { ProductSellDTO, CreateProductSellDTO, CategoryDTO } from "../../../interfaces";
import { usePut, useFetch } from "../../../hooks";

type Props = {
    listing: ProductSellDTO;
};

export const ListingRow = ({ listing }: Props) => {
    const showToast = useToast();
    const [isModalOpen, setIsModalOpen] = useState(false);
    const { put } = usePut<ProductSellDTO>(`/api/v1/product-listings/${listing.id}`);
    const { data: categories } = useFetch<CategoryDTO[]>("/api/v1/categories", false);

    const onUpdate = async (data: CreateProductSellDTO) => {
        try {
            await put(data);
            showToast("confirm", "Publicación actualizada con éxito");
            setIsModalOpen(false);
        } catch {
            showToast("error", "Error al actualizar la publicación");
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
                    <button onClick={() => showToast("confirm", `Eliminar ${listing.product.name}`)} className="ml-btn ml-btn--delete">
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