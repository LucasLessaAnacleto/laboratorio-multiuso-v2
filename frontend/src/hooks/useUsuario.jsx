// hooks/useUsuario.js
import { useContext, useEffect } from "react";
import { UsuarioContext } from "../contexts/UsuarioContext";
import { useLoading } from "./useLoading";

export function useUsuario() {
    const { usuario, carregando } = useContext(UsuarioContext);
    const { loading, setLoading } = useLoading();   
    useEffect(() => {
        setLoading(carregando);
    }, [carregando, setLoading]);

    return { usuario, carregando, loading, setLoading };
}
