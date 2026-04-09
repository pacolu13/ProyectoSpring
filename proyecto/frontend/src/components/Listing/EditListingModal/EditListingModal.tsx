import { useForm } from "react-hook-form";
import type { ProductSellDTO, CreateProductSellDTO, CategoryDTO } from "../../../interfaces";
import { ProductForm } from "../../../pages/Seller/ProductForm";
import "./EditListingModal.css";

type Props = {
    listing: ProductSellDTO;
    categories?: CategoryDTO[] | null;
    onClose: () => void;
    onSubmit: (data: CreateProductSellDTO) => void;
};

export const EditListingModal = ({ listing, categories, onClose, onSubmit }: Props) => {
    const { register, handleSubmit, watch } = useForm<CreateProductSellDTO>({
        defaultValues: {
            title: listing.title,
            description: listing.description,
            price: listing.price,
            stock: listing.stock,
            state: listing.state,
            product: listing.product, // mismo shape, sin mapeo
        },
    });

    return (
        <div className="modal-overlay" onClick={onClose}>
            <div className="modal-card" onClick={e => e.stopPropagation()}>
                <div className="modal-header">
                    <h3 className="modal-title">Editar publicación</h3>
                    <button className="modal-close" onClick={onClose}>✕</button>
                </div>

                <div className="modal-body">
                    <ProductForm register={register} watch={watch} categories={categories} />
                </div>

                <div className="modal-footer">
                    <button className="modal-btn modal-btn--cancel" onClick={onClose}>
                        Cancelar
                    </button>
                    <button className="modal-btn modal-btn--save" onClick={handleSubmit(onSubmit)}>
                        Guardar
                    </button>
                </div>
            </div>
        </div>
    );
};