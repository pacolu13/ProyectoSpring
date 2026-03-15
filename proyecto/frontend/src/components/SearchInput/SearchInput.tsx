import { useState } from "react";
import "./SearchInput.css"

interface SearchInputProps {
    onSearch: (value: string) => void;
    placeholder?: string;
    disabled?: boolean;
}

export const SearchInput = ({ onSearch, placeholder = "Buscar...", disabled }: SearchInputProps) => {
    const [query, setQuery] = useState("");

    const handleSearch = () => {
        if (query.trim()) onSearch(query.trim());
    };

    return (
        <div className="search_container">
            <div className="search_wrapper">
                <input
                    className="search_input"
                    type="text"
                    placeholder={placeholder}
                    value={query}
                    onChange={(e) => setQuery(e.target.value)}
                    onKeyDown={(e) => e.key === "Enter" && handleSearch()}
                    disabled={disabled}
                />
                <button onClick={handleSearch} disabled={disabled}><i className="fa fa-search" /></button>

            </div>
        </div>
    );
};