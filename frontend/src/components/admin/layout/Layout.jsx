import { useState } from "react";
import { Sidebar } from "./Sidebar.jsx";
import { Header } from "./Header.jsx";

import "./style/layout.css";

export function Layout({ title, children }) {
    document.title = title;
    const [sidebarOpen, setSidebarOpen] = useState(false);
    return (
        <div id="admin-page">
            <Header onToggleSidebar={() => setSidebarOpen(estado => !estado)} />
            <div className="admin-container">
                <Sidebar open={sidebarOpen}/>
                <main>
                    {children}
                </main>
            </div>
        </div>
    );
}