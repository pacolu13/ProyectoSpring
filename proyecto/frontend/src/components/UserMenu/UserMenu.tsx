import { useRef, useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../../context/AuthContext";
import "./UserMenu.css";
import { AvatarIcon, CartIcon, LogoutIcon } from "../../styles/menuIcons";
import { LinkIcon } from "./LinkIcon/LinkIcon";


export const UserMenu = () => {
  const [open, setOpen] = useState(false);
  const { token, user, logout } = useAuth();
  const isSeller = user?.roles.includes("SELLER");
  const navigate = useNavigate();
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

  const handleLogout = () => {
    logout();
    setOpen(false);
    navigate("/login");
  };

  return (
    <div className="user_menu_root" ref={ref}>
      <button
        className={`user_btn${open ? " user_btn--active" : ""}`}
        onClick={() => setOpen((v) => !v)}
        aria-label="Menú de usuario"
        aria-expanded={open}
      >
        <span className="user_btn__avatar"><AvatarIcon /></span>
        <span className="user_btn__chevron">
          <svg viewBox="0 0 10 6" fill="none">
            <path d="M1 1l4 4 4-4" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round" />
          </svg>
        </span>
      </button>

      <div className={`user_dropdown${open ? " user_dropdown--visible" : ""}`} role="menu">
        <div className="user_dropdown__header">
          <div className="user_dropdown__avatar_lg"><AvatarIcon /></div>
          <div className="user_dropdown__info">
            <span className="user_dropdown__email">{user?.email}</span>
          </div>
        </div>

        <div className="user_dropdown__actions">
          {token ? (
            <>
              <LinkIcon label="Mi carrito" url="/cart" icon={<CartIcon />} />

              {isSeller && (
                <LinkIcon label="Mis listados" url="/manage-listings" icon={<CartIcon />} />
              )}
              {
                isSeller && (
                  <LinkIcon label="Vender producto" url="/products-sell" icon={<CartIcon />} />
                )
              }

              <button className="user_dropdown__item user_dropdown__item--danger" onClick={handleLogout}>
                <span className="user_dropdown__item__icon"><LogoutIcon /></span>
                Cerrar sesión
              </button>
            </>
          ) : (
            <LinkIcon label="Iniciar sesión" url="/login" icon={<LogoutIcon />} />
          )}
        </div>
      </div>
    </div>
  );
};