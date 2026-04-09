import { usePost, useFetch } from "../../../hooks";
import type { ProductSellDTO, CategoryDTO, CreateProductSellDTO } from "../../../interfaces";
import { Button, useToast } from "../../../components";
import { useForm } from "react-hook-form";
import { ProductForm } from "../ProductForm";
import "./ProductSell.css";

export const ProductSell = () => {
  const showToast = useToast();
  const { data: categories } = useFetch<CategoryDTO[]>("/api/v1/categories", false);
  const { post } = usePost<ProductSellDTO>("/api/v1/product-listings");

  const { register, handleSubmit, watch, reset } = useForm<CreateProductSellDTO>();

  const onSubmit = async (data: CreateProductSellDTO) => {
    try {
      await post(data); 
      showToast("confirm", "Publicación creada con éxito");
      reset();
    } catch {
      showToast("error", "Error al crear la publicación");
    }
  };

  return (
    <div className="ps-root">
      <div className="ps-card">
        <ProductForm register={register} watch={watch} categories={categories} />
        <Button label="Publicar" parentMethod={handleSubmit(onSubmit)} />
      </div>
    </div>
  );
};