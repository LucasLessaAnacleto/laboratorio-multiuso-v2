
import { useEffect, useState } from "react";
import { authService } from "../services/authService.js";
import { UsuarioContext } from "./UsuarioContext.jsx";

export function UsuarioProvider({ children }) {
  const [usuario, setUsuario] = useState(null);
  const [carregando, setCarregando] = useState(true);

  useEffect(() => {
    async function carregarUsuario() {
        try {
          const dados = await authService.getUsuario();
          setUsuario(dados);
        } catch (err) {
          console.error("Erro ao buscar usuário:", err);
          authService.logout();
        } finally {
          setCarregando(false);
        }
    }
    carregarUsuario();
  }, []);

  return (
    <UsuarioContext.Provider
      value={{
        usuario,
        carregando,
      }}
    >
      {children}
    </UsuarioContext.Provider>
  );
}