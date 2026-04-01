// EditListingModal.tsx
import { useForm } from "react-hook-form";
import { InputField } from "../../../components";
import type { ProductSellDTO } from "../../../interfaces";
import "./EditListingModal.css";

type Props = {
    listing: ProductSellDTO;
    onClose: () => void;
};

type EditFormDTO = {
    title: string;
    productName: string;
    price: string;
    category: string;
    quantity: number;
    paused: boolean;
};

export const EditListingModal = ({ listing, onClose }: Props) => {
    const { register, handleSubmit } = useForm<EditFormDTO>({
        defaultValues: {
            title: listing.title,
            productName: listing.product.name,
            price: String(listing.price),
            quantity: listing.quantity,
        },
    });

    const onSubmit = (data: EditFormDTO) => {
        console.log("Actualizar:", data);
        onClose();
    };

    return (
        <div className="modal-overlay" onClick={onClose}>
            <div className="modal-card" onClick={e => e.stopPropagation()}>

                <div className="modal-header">
                    <h3 className="modal-title">Editar publicación</h3>
                    <button className="modal-close" onClick={onClose}>✕</button>
                </div>

                <div className="modal-body">
                    <InputField
                        label="Título"
                        name="title"
                        register={register}
                        rules={{ required: "El título es obligatorio" }}
                    />
                    <InputField
                        label="Producto"
                        name="productName"
                        register={register}
                    />
                    <div className="modal-row">
                        <InputField
                            label="Precio"
                            name="price"
                            type="number"
                            register={register}
                            rules={{ required: "El precio es obligatorio", min: { value: 1, message: "Debe ser mayor a 0" } }}
                        />
                        <InputField
                            label="Stock"
                            name="quantity"
                            type="number"
                            register={register}
                            rules={{ required: "El stock es obligatorio", min: { value: 1, message: "Debe ser mayor a 0" } }}
                        />
                    </div>
                    <InputField
                        label="Categoría"
                        name="category"
                        register={register}
                    />

                    <div className="modal-toggle">
                        <label className="modal-toggle__label">Pausar publicación</label>
                        <input
                            type="checkbox"
                            className="modal-toggle__checkbox"
                            {...register("paused")}
                        />
                    </div>
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