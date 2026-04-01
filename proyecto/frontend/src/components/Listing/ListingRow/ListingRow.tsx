// ListingRow.tsx
import { useState } from "react";
import { useToast, EditListingModal } from "../../../components";
import type { ProductSellDTO } from "../../../interfaces";

type Props = {
    listing: ProductSellDTO;
};

export const ListingRow = ({ listing }: Props) => {
    const showToast = useToast();
    const [isModalOpen, setIsModalOpen] = useState(false);

    return (
        <>
            <tr key={listing.id}>
                <td>{listing.product.name}</td>
                <td>${listing.price.toFixed(2)}</td>
                <td>{listing.quantity}</td>
                <td className="ml-actions">
                    <button
                        onClick={() => setIsModalOpen(true)}
                        className="ml-btn ml-btn--edit"
                    >
                        Editar
                    </button>
                    <button
                        onClick={() => showToast("confirm", `Eliminar ${listing.product.name}`)}
                        className="ml-btn ml-btn--delete"
                    >
                        Eliminar
                    </button>
                </td>
            </tr>

            {isModalOpen && (
                <EditListingModal
                    listing={listing}
                    onClose={() => setIsModalOpen(false)}
                />
            )}
        </>
    );
};