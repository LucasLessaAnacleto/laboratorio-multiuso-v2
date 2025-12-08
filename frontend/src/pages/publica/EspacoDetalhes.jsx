import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { espacoService } from "../../services/espacoService.js"; 

export function EspacoDetalhes() {
  document.title = "Espaço - Lab Multiuso";
  const { id } = useParams();
  const [espaco, setEspaco] = useState(null);

  useEffect(() => {
    async function carregar() {
      const dados = await espacoService.buscar(id);
      setEspaco(dados);
    }
    carregar();
  }, [id]);

  if (!espaco) return <div>Carregando...</div>;

  return (
    <div>
      <h1>{espaco.nome}</h1>
      <p>{espaco.descricao}</p>
      <p>Endereço: {espaco.endereco}</p>
      <img src={espaco.imagemCapa} alt={espaco.nome} />
    </div>
  );
}
