import { useState, useRef, useEffect } from "react";
import "./UserMenu.css";

interface MenuItem {
  icon: string;
  label: string;
  href?: string;
  danger?: boolean;
}

const menuItems: MenuItem[] = [
  { icon: "👤", label: "Mi perfil" },
  { icon: "🛍️", label: "Mis pedidos" },
  { icon: "❤️", label: "Favoritos" },
  { icon: "⚙️", label: "Configuración" },
  { icon: "🚪", label: "Cerrar sesión", danger: true },
];

export const UserMenu = () => {
  const [open, setOpen] = useState(false);
  const ref = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const handler = (e: MouseEvent) => {
      if (ref.current && !ref.current.contains(e.target as Node)) {
        setOpen(false);
      }
    };
    document.addEventListener("mousedown", handler);
    return () => document.removeEventListener("mousedown", handler);
  }, []);

  return (
    <div className="user_menu_root" ref={ref}>
      <button
        className={`user_btn ${open ? "user_btn--active" : ""}`}
        onClick={() => setOpen((v) => !v)}
        aria-label="Menú de usuario"
        aria-expanded={open}
      >
        <span className="user_btn__avatar">
          <svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
            <circle cx="12" cy="8" r="4" stroke="currentColor" strokeWidth="1.5" />
            <path
              d="M4 20c0-4 3.582-7 8-7s8 3 8 7"
              stroke="currentColor"
              strokeWidth="1.5"
              strokeLinecap="round"
            />
          </svg>
        </span>
        <span className={`user_btn__chevron ${open ? "user_btn__chevron--up" : ""}`}>
          <svg viewBox="0 0 10 6" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M1 1l4 4 4-4" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round" />
          </svg>
        </span>
      </button>

      <div className={`user_dropdown ${open ? "user_dropdown--visible" : ""}`} role="menu">
        {/* Header del menú */}
        <div className="user_dropdown__header">
          <div className="user_dropdown__avatar_lg">
            <svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
              <circle cx="12" cy="8" r="4" stroke="currentColor" strokeWidth="1.5" />
              <path d="M4 20c0-4 3.582-7 8-7s8 3 8 7" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" />
            </svg>
          </div>
          <div className="user_dropdown__info">
            <span className="user_dropdown__name">Usuario</span>
            <span className="user_dropdown__email">usuario@email.com</span>
          </div>
        </div>

        <div className="user_dropdown__divider" />

        {/* Items */}
        <ul className="user_dropdown__list">
          {menuItems.map((item, i) => (
            <li key={i}>
              <button
                className={`user_dropdown__item ${item.danger ? "user_dropdown__item--danger" : ""}`}
                role="menuitem"
                onClick={() => setOpen(false)}
              >
                <span className="user_dropdown__item_icon">{item.icon}</span>
                <span>{item.label}</span>
              </button>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}
