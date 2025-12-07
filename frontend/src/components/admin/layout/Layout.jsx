import { useState } from "react";
import { Sidebar } from "./Sidebar.jsx";
import { Header } from "./Header.jsx";
import { useLoading } from "../../../hooks/useLoading.jsx";

import "./style/layout.css";

export function Layout({ title, children, usuario }) {
    document.title = title;
    const [sidebarOpen, setSidebarOpen] = useState(false);
    const { loading } = useLoading();
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