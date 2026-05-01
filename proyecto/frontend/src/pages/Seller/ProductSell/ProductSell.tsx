import { usePost, useFetch } from "../../../hooks";
import type { ProductSellDTO, CategoryDTO, CreateProductSellDTO } from "../../../interfaces";
import { Button, useToast } from "../../../components";
import { useForm } from "react-hook-form";
import { ProductForm } from "../ProductForm";
import { productsRoutes, categoriesRoutes } from "../../../api/routes";
import "./ProductSell.css";

export const ProductSell = () => {
  const showToast = useToast();
  const { data: categories } = useFetch<CategoryDTO[]>(categoriesRoutes.list, false);
  const { post } = usePost<ProductSellDTO>(productsRoutes.create);

  const { register, handleSubmit, watch, reset } = useForm<CreateProductSellDTO>();

  const onSubmit = async (data: CreateProductSellDTO) => {
    try {
      console.log(data);
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