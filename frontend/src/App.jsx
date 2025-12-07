import { Navigate, Route, Routes } from "react-router-dom";
import { Dashboard } from "./pages/admin/Dashboard.jsx";
import { Equipamentos } from "./pages/admin/Equipamentos.jsx";

export function App() {
  return (
    <Routes>

      {/* Página inicial redireciona para o Dashboard */}
      <Route path="/" element={<Navigate to="/admin" />} />

      {/* Redireciona /admin → /admin/dashboard */}
      <Route path="/admin" element={<Navigate to="/admin/dashboard" replace />} />

      {/* Rotas Admin */}
      <Route path="/admin/dashboard" element={<Dashboard />} />
      <Route path="/admin/equipamentos" element={<Equipamentos />} />

      {/* 404 */}
      {/* Fahur: Página notfound*/}
      <Route path="*" element={<div className="page404"><h1>404 - Página não encontrada</h1></div>} />

    </Routes>
  )
}
