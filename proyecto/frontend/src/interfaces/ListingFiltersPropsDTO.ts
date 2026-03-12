export type SortOption = "none" | "price-asc" | "price-desc";

export interface ListingFiltersPropsDTO {
    onMaxPriceChange?: (value: number | null) => void;
    onSortChange?: (sort: SortOption) => void;
    onClear?: () => void;
    initialMaxPrice?: number | null;
    initialSort?: SortOption;
}

export const SORT_OPTIONS: { value: SortOption; label: string }[] = [
    { value: "none", label: "Relevancia" },
    { value: "price-asc", label: "Menor precio" },
    { value: "price-desc", label: "Mayor precio" },
];
