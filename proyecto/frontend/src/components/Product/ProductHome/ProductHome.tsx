import { useState } from "react";
import { Button } from "../../Button/Button";
import "./ProductHome.css"
import { useToast } from "../../Toast/ToastProvider/ToastContext";
import { useDelete } from "../../../hooks";

interface Props {
    id?: number,
    idProduct?: number,
    image: string,
    brand: string,
    title: string,
    description: string
    isAdmin?: boolean

}

export const ProductHome = ({ id, idProduct, image, brand, title, description, isAdmin }: Props) => {
    const [imageError, setImageError] = useState(false);
    const showToast = useToast();
    const { constDelete } = useDelete<void>(`/api/v1/admin/listings/${id}`)
    const navegateToProduct = () => {
        window.location.href = `/products/${idProduct}`;
    }

    const handleDelete = async () => {
        try {
            console.log("Eliminando publicación con ID:", id);
            await constDelete();
            showToast("confirm", "Publicación eliminada exitosamente");
        } catch (error) {
            showToast("error", "Error al eliminar la publicación");
        }
    }

    return <>
        <div key={id} className="product-card">

            <div className="product-image">
                {!imageError && image ? (
                    <img
                        src={image}
                        alt={title}
                        onError={() => setImageError(true)}
                    />
                ) : (
                    <i className="fa-solid fa-image product-placeholder-icon"></i>
                )}
            </div>

            <div className="product-info">
                <span className="product-brand">{brand}</span>
                <h3>{title}</h3>
                <p>{description}</p>

                {isAdmin && (
                    <button className="btn-edit"
                        onClick={handleDelete}>
                        Eliminar publicación
                    </button>
                )}
                <Button label='Ver producto' parentMethod={navegateToProduct} />
            </div>

        </div>
    </>
}