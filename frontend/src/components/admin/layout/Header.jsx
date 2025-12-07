import { useState, useRef, useEffect, useContext } from "react";
import IconMenu from "../../../assets/icon-menu.svg?react";

import "./style/header.css";

export function Header({ onToggleSidebar, usuario }) {
    const [open, setOpen] = useState(false);
    const popupRef = useRef(null);
    const ftPerfilPadrao = 'https://lh3.googleusercontent.com/a/ACg8ocJZlvwirqzkaYy1OLeD1D7hGlJyXG8H-cNM2YYLgPkg5v0p8bR3=s288-c-no';
    
    useEffect(() => {
        function handleClickOutside(e) {
            if (popupRef.current && !popupRef.current.contains(e.target)) {
                setOpen(false);
            }
        }
        document.addEventListener("mousedown", handleClickOutside);
        return () => document.removeEventListener("mousedown", handleClickOutside);
    }, []);

    return (
        <header className="header shadow">
            <div className="left">
                <button className="menu-btn" onClick={onToggleSidebar}>
                    <IconMenu />
                </button>
            </div>

            <div className="user-area" ref={popupRef}>
                <div className="user-icon" onClick={() => setOpen((o) => !o)}>
                    <img src={usuario?.fotoPerfil || ftPerfilPadrao} alt="foto perfil" className="mini-foto-perfil" />
                </div>

                {open && (
                    <div className="user-popup">
                        <div className="user-info">
                            <strong>{ usuario.nome }</strong>
                            <span>{usuario.email}</span>
                        </div>

                        <hr />

                        <button className="popup-btn">Editar perfil</button>
                        <button className="popup-btn logout">Sair</button>
                    </div>
                )}
            </div>
        </header>
    );
}
