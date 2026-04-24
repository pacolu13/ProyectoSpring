import { useState } from "react";
import { Button } from "../../Button/Button";
import "./ProductHome.css"
import { useToast } from "../../Toast/ToastProvider/ToastContext";

interface Props {
    id?: number,
    image: string,
    brand: string,
    title: string,
    description: string
    isAdmin?: boolean

}

export const ProductHome = ({ id, image, brand, title, description, isAdmin }: Props) => {
    const [imageError, setImageError] = useState(false);
    const navegateToProduct = () => {
        window.location.href = `/products/${id}`;
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
                        onClick={() => alert("Funcionalidad de edición no implementada")}>
                        Eliminar publicación
                    </button>
                )}
                <Button label='Ver producto' parentMethod={navegateToProduct} />
            </div>

        </div>
    </>
}