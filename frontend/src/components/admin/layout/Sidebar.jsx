import { NavLink } from "react-router-dom";
import IconCalendario from "../../../assets/sidebar-calendario.svg?react";
import IconEquipamento from "../../../assets/sidebar-equip.svg?react";
import IconEspaco from "../../../assets/sidebar-esp.svg?react";
import IconHome from "../../../assets/sidebar-home.svg?react";

import './style/sidebar.css';

export function Sidebar({ open }) {
    const menu = [
        { Icon: IconHome, label: "Dashboard", to: "/admin" },
        { Icon: IconCalendario, label: "Calendário", to: "/admin/calendario" },
        { Icon: IconEquipamento, label: "Equipamentos", to: "/admin/equipamentos" },
        { Icon: IconEspaco, label: "Espaços", to: "/admin/espacos" },
        // { icon: iconConfig, label: "Configurações", to: "/admin/config" },
    ];
    return (
        <div className={`sidebar ${open ? 'open' : ''}`}>
            <nav><ul>
                {menu.map((item) => (
                    <li key={item.to}>
                        <NavLink
                            to={item.to}
                            end
                            className={({ isActive }) =>
                            isActive ? "menu-item active" : "menu-item"
                            }
                        >
                            <item.Icon className="menu-icon" />
                            <span>{item.label}</span>
                        </NavLink>
                    </li>
                ))}
            </ul></nav>
        </div>
    )
}