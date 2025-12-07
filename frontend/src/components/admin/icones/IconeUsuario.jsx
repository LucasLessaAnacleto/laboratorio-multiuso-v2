
export function IconeUsuario({ tamanho, ftPerfil }) {
    return (
        <div className="icone" style={{ width: tamanho || "auto" }}>
            <img src={ftPerfil ?? "ft_perfil_default.png"} 
                alt="Foto de perfil do usuário" />
        </div>
    )
}