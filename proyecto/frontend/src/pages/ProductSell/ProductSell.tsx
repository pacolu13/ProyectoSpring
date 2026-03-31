import { usePost, useFetch } from "../../hooks";
import type { ProductSellDTO, CategoryDTO, FormDataDTO } from "../../interfaces";
import { Button, useToast, InputField, SelectField, StatesField, TextAreaField } from "../../components";
import { useForm } from "react-hook-form";
import "./ProductSell.css";

const STATES = ["Nuevo", "Usado", "No funciona"];

export const ProductSell = () => {
  const showToast = useToast();

  const { data: categories } = useFetch<CategoryDTO[]>("/api/v1/categories", false);
  const { post } = usePost<ProductSellDTO>("/api/v1/product-listings");

  const {
    register,
    handleSubmit,
    watch,
    reset,
  } = useForm<FormDataDTO>({
    defaultValues: {
      state: STATES[0],
    },
  });

  const category = watch("category");
  const image = watch("image");

  const isOtherCategory = category === "Otra";

  const onSubmit = async (data: FormDataDTO) => {
    const payload: ProductSellDTO = {
      title: data.title,
      price: parseFloat(data.price),
      state: data.state,
      quantity: data.quantity,
      product: {
        name: data.productName,
        description: data.description,
        brand: data.brand,
        category: isOtherCategory ? data.customCategory : data.category,
        image: data.image,
        active: true,
      },
    };

    const result = await post(payload);

    if (result) {
      showToast("confirm", "Publicación creada exitosamente");
      reset();
    } else {
      showToast("error", "Error al crear la publicación");
    }
  };

  return (
    <div className="ps-root">
      <div className="ps-card">

        <div className="ps-section">
          <h2 className="ps-section__title">Publicación</h2>

          <InputField
            label="Título"
            name="title"
            register={register}
            rules={{ required: "El título es obligatorio" }}
            placeholder="Iphone 13 pro max"
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
              name="quantity"
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

        <div className="ps-section">
          <h2 className="ps-section__title">Producto (opcional)</h2>

          <div className="ps-row">
            <InputField
              label="Nombre del producto"
              name="productName"
              register={register}
              placeholder="Ej: iPhone 13 Pro"
            />
            <InputField
              label="Marca"
              name="brand"
              register={register}
              placeholder="Ej: Apple"
            />
          </div>

          <TextAreaField
            label="Descripción"
            name="description"
            register={register}
            placeholder="Descripción"
          />
        </div>

        <SelectField label="Categoría" name="category" register={register}>
          {categories?.map(cat => (
            <option key={cat.id} value={cat.id}>
              {cat.name}
            </option>
          ))}
        </SelectField>

        {isOtherCategory && (
          <input
            className="ps-input"
            {...register("customCategory")}
            placeholder="Especificá la categoría"
          />
        )}

        <InputField
          label="URL de imagen"
          name="image"
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

        <Button label="Publicar" parentMethod={handleSubmit(onSubmit)} />
      </div>
    </div>
  );
};