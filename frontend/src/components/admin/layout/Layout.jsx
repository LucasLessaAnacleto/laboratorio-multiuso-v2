import { useContext, useState } from "react";
import { Sidebar } from "./Sidebar.jsx";
import { Header } from "./Header.jsx";
import { useLoading } from "../../../hooks/useLoading.jsx";

import "./style/layout.css";
import { UsuarioContext } from "../../../contexts/UsuarioContext.jsx";

export function Layout({ title, children }) {
    document.title = title;
    const [sidebarOpen, setSidebarOpen] = useState(false);
    const { loading } = useLoading();
    const { usuario } = useContext(UsuarioContext);
    return (
        <div id="admin-page">
            <Header onToggleSidebar={() => setSidebarOpen(estado => !estado)} usuario={usuario} />
            <div className="admin-container">
                <Sidebar open={sidebarOpen}/>
                <main>
                    {children}
                    {loading && (
                        <div className="loading-overlay">
                            <div className="loader"></div>
                        </div>
                    )}
                </main>
            </div>
        </div>
    );
}