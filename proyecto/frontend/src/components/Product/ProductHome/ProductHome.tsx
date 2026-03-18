import { useState } from "react";
import { Button } from "../Button/Button";
import "./ProductHome.css"

interface Props {
    id: number,
    image: string,
    brand: string,
    name: string,
    description: string

}

export const ProductHome = ({ id, image, brand, name, description }: Props) => {

    const [imageError, setImageError] = useState(false);

    return <>
        <div key={id} className="product-card">

            <div className="product-image">
                {!imageError && image ? (
                    <img
                        src={image}
                        alt={name}
                        onError={() => setImageError(true)}
                    />
                ) : (
                    <i className="fa-solid fa-image product-placeholder-icon"></i>
                )}
            </div>

            <div className="product-info">
                <span className="product-brand">{brand}</span>
                <h3>{name}</h3>
                <p>{description}</p>

                <Button label='Ver producto' parentMethod={() => { window.location.href = `/products/${id}`; }}></Button>
            </div>

        </div>
    </>
}