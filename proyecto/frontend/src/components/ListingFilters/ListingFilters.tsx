import { useState } from "react";
import "./ListingFilters.css"
import type { ListingFiltersPropsDTO, SortOption } from "../../interfaces/ListingFiltersPropsDTO";
import { SORT_OPTIONS } from "../../interfaces/ListingFiltersPropsDTO";

export const ListingFilters = ({
  onMaxPriceChange,
  onSortChange,
  onClear,
  initialMaxPrice = null,
  initialSort = "none",
}: ListingFiltersPropsDTO) => {
  const [maxPrice, setMaxPrice] = useState<number | null>(initialMaxPrice);
  const [sort, setSort] = useState<SortOption>(initialSort);

  const handleMaxPriceChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value = e.target.value === "" ? null : Number(e.target.value);
    setMaxPrice(value);
    onMaxPriceChange?.(value);
  };

  const handleSortChange = (value: SortOption) => {
    setSort(value);
    onSortChange?.(value);
  };

  const handleClear = () => {
    setMaxPrice(null);
    setSort("none");
    onClear?.();
  };

  return (
    <aside className="listing-filters">
      <p className="listing-filters__title">Filtros</p>

      <div className="listing-filters__group">
        <label className="listing-filters__label">Precio máximo</label>
        <input
          className="listing-filters__input"
          type="number"
          placeholder="$ sin límite"
          value={maxPrice ?? ""}
          onChange={handleMaxPriceChange}
        />
      </div>

      <div className="listing-filters__group">
        <label className="listing-filters__label">Ordenar por</label>
        <div className="listing-filters__sort">
          {SORT_OPTIONS.map((opt) => (
            <button
              key={opt.value}
              className={`listing-filters__sort-btn${sort === opt.value ? " active" : ""
                }`}
              onClick={() => handleSortChange(opt.value)}
            >
              {opt.label}
            </button>
          ))}
        </div>
      </div>

      <button className="listing-filters__clear" onClick={handleClear}>
        Limpiar filtros
      </button>
    </aside>
  );
}
