import { useState } from "react";
import { usePost, useFetch } from "../../hooks";
import { useToast } from "../../components";
import type { ProductSellDTO } from "../../interfaces";
import "./ProductSell.css";

const STATES = ["Nuevo", "Usado", "No funciona"];

export const ProductSell = () => {
  const showToast = useToast();
  const { data: categories } = useFetch<string[]>("/api/v1/categories", false);
  const { post } = usePost<ProductSellDTO>("/api/v1/product-listings");

  const [title, setTitle] = useState("");
  const [price, setPrice] = useState("");
  const [stock, setStock] = useState("");
  const [state, setState] = useState(STATES[0]);

  const [productName, setProductName] = useState("");
  const [description, setDescription] = useState("");
  const [brand, setBrand] = useState("");
  const [category, setCategory] = useState("");
  const [customCategory, setCustomCategory] = useState("");
  const [image, setImage] = useState("");

  const isOtherCategory = category === "Otra";

  const handleSubmit = async () => {
    if (!title || !price || !stock) {
      showToast("warning", "Completá los campos obligatorios");
      return;
    }

    const payload: ProductSellDTO = {
      title,
      price: parseFloat(price),
      quantity: parseInt(stock),
      state,
      stock: parseInt(stock),
      product: {
        id: 0,
        name: productName,
        description,
        brand,
        category: isOtherCategory ? customCategory : category,
        image,
        active: true,
        creationDate: new Date().toISOString(),
      },
    };

    const result = await post(payload);

    if (result) {
      showToast("confirm", "Publicación creada exitosamente");
      handleReset();
    } else {
      showToast("error", "Error al crear la publicación");
    }
  };

  const handleReset = () => {
    setTitle(""); setPrice(""); setStock(""); setState(STATES[0]);
    setProductName(""); setDescription(""); setBrand("");
    setCategory(""); setCustomCategory(""); setImage("");
  };

  return (
    <div className="ps-root">
      <div className="ps-card">

        <div className="ps-section">
          <h2 className="ps-section__title">Publicación</h2>

          <div className="ps-field">
            <label className="ps-label">Título <span className="ps-required">*</span></label>
            <input className="ps-input" value={title} onChange={e => setTitle(e.target.value)} placeholder="Ej: iPhone 13 Pro 256gb" />
          </div>

          <div className="ps-row">
            <div className="ps-field">
              <label className="ps-label">Precio por unidad <span className="ps-required">*</span></label>
              <input className="ps-input" type="number" min="0" value={price} onChange={e => setPrice(e.target.value)} placeholder="0.00" />
            </div>
            <div className="ps-field">
              <label className="ps-label">Stock <span className="ps-required">*</span></label>
              <input className="ps-input" type="number" min="1" value={stock} onChange={e => setStock(e.target.value)} placeholder="0" />
            </div>
          </div>

          <div className="ps-field">
            <label className="ps-label">Estado <span className="ps-required">*</span></label>
            <div className="ps-state-group">
              {STATES.map(s => (
                <button key={s} className={`ps-state-btn ${state === s ? "ps-state-btn--active" : ""}`} onClick={() => setState(s)}>
                  {s}
                </button>
              ))}
            </div>
          </div>
        </div>

        <div className="ps-divider" />

        <div className="ps-section">
          <h2 className="ps-section__title">Producto <span className="ps-optional">(opcional)</span></h2>

          <div className="ps-row">
            <div className="ps-field">
              <label className="ps-label">Nombre del producto</label>
              <input className="ps-input" value={productName} onChange={e => setProductName(e.target.value)} placeholder="Ej: iPhone 13 Pro" />
            </div>
            <div className="ps-field">
              <label className="ps-label">Marca</label>
              <input className="ps-input" value={brand} onChange={e => setBrand(e.target.value)} placeholder="Ej: Apple" />
            </div>
          </div>

          <div className="ps-field">
            <label className="ps-label">Descripción</label>
            <textarea className="ps-input ps-textarea" value={description} onChange={e => setDescription(e.target.value)} placeholder="Describí el producto..." />
          </div>

          <div className="ps-field">
            <label className="ps-label">Categoría</label>
            <select className="ps-input ps-select" value={category} onChange={e => setCategory(e.target.value)}>
              <option value="">Seleccioná una categoría</option>
              {categories?.map(cat => (
                <option key={cat} value={cat}>{cat}</option>
              ))}
              <option value="Otra">Otra</option>
            </select>
          </div>

          {isOtherCategory && (
            <div className="ps-field">
              <label className="ps-label">Especificá la categoría</label>
              <input className="ps-input" value={customCategory} onChange={e => setCustomCategory(e.target.value)} placeholder="Ej: Instrumentos musicales" />
            </div>
          )}

          <div className="ps-field">
            <label className="ps-label">URL de imagen</label>
            <input className="ps-input" value={image} onChange={e => setImage(e.target.value)} placeholder="https://..." />
            {image && <img className="ps-preview" src={image} alt="preview" onError={e => (e.currentTarget.style.display = "none")} />}
          </div>
        </div>

        <button className="ps-submit" onClick={handleSubmit}>
          Publicar
        </button>

      </div>
    </div>
  );
};