import type { UseFormRegister, UseFormWatch } from "react-hook-form";
import { InputField, SelectField, StatesField, TextAreaField } from "../../components";
import type { CategoryDTO, CreateProductSellDTO } from "../../interfaces";

const STATES = ["NEW", "USED", "DAMAGED"];

interface ProductFormProps {
    register: UseFormRegister<CreateProductSellDTO>;
    watch: UseFormWatch<CreateProductSellDTO>;
    categories?: CategoryDTO[] | null;
}

export const ProductForm = ({ register, watch, categories }: ProductFormProps) => {
    const category = watch("product.category");
    const image = watch("product.image");
    const isOtherCategory = category === "Otra";

    return (
        <>
            {/* ── Publicación ── */}
            <div className="ps-section">
                <h2 className="ps-section__title">Publicación</h2>

                <InputField
                    label="Título"
                    name="title"
                    register={register}
                    rules={{ required: "El título es obligatorio" }}
                />
                <InputField
                    label="Descripción"
                    name="description"
                    register={register}
                />
                <div className="ps-row">
                    <InputField
                        label="Precio"
                        name="price"
                        type="number"
                        register={register}
                        rules={{ required: "El precio es obligatorio", min: { value: 1, message: "Debe ser mayor a 0" } }}
                    />
                    <InputField
                        label="Stock"
                        name="stock"
                        type="number"
                        register={register}
                        rules={{ required: "El stock es obligatorio", min: { value: 1, message: "Debe ser mayor a 0" } }}
                    />
                </div>
                <StatesField
                    label="Estado"
                    name="state"
                    states={STATES}
                    register={register}
                    watch={watch}
                />
            </div>

            <div className="ps-divider" />

            {/* ── Producto ── */}
            <div className="ps-section">
                <h2 className="ps-section__title">Producto (opcional)</h2>

                <div className="ps-row">
                    <InputField
                        label="Nombre del producto"
                        name="product.name"
                        register={register}
                        placeholder="Ej: iPhone 13 Pro"
                    />
                    <InputField
                        label="Marca"
                        name="product.brand"
                        register={register}
                        placeholder="Ej: Apple"
                    />
                </div>
                <TextAreaField
                    label="Descripción del producto"
                    name="product.description"
                    register={register}
                    placeholder="Descripción"
                />
                <SelectField label="Categoría" name="product.category" register={register}>
                    {categories?.map(cat => (
                        <option key={cat.id} value={cat.name}>{cat.name}</option>
                    ))}
                    <option value="Otra">Otra</option>
                </SelectField>
                {isOtherCategory && (
                    <InputField
                        label="Categoría personalizada"
                        name="product.customCategory"
                        register={register}
                        placeholder="Especificá la categoría"
                    />
                )}
                <InputField
                    label="URL de imagen"
                    name="product.image"
                    register={register}
                    placeholder="URL de imagen"
                />
                {image && (
                    <img
                        className="ps-preview"
                        src={image}
                        alt="preview"
                        onError={e => (e.currentTarget.style.display = "none")}
                    />
                )}
            </div>
        </>
    );
};