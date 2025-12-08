import { ConfirmModal } from "../ConfirmModal";
import { Modal } from "../Modal";

export function EquipamentoModal({ itemView, itemEdit, itemDel, actions = { view: {}, edit: {}, del: {} } }){
    const dadosIniciaisEquipamento = {
        id: 2,
        nome: "Equipamento de Informática",
        categoria: "Aparelho Eletrônico",
        descricao: "Computador desktop de mesa 256gb",
        patrimonio: "1235435634",
        disponivel: true,
        contatoResponsavel: "",
        espaco: "Laboratório Moda Senac",
        imagem: null
    };

    const camposEditar = [
        { nome: "id", label: "ID", tipo: "number", readonly: true },
        { nome: "nome", label: "Nome", tipo: "text" },
        { nome: "categoria", label: "Categoria", tipo: "text" },
        { nome: "descricao", label: "Descrição", tipo: "textarea" },
        { nome: "patrimonio", label: "Patrimônio", tipo: "text" },
        { nome: "espaco", label: "Espaço", tipo: "text", readonly: true },
        { nome: "disponivel", label: "Disponível", tipo: "switch" },
        { nome: "contatoResponsavel", label: "Contato do Responsável", tipo: "text" },
        { nome: "imagem", label: "Imagem do Equipamento", tipo: "file" }
    ];

    const camposView = [
        { nome: "id", label: "ID", tipo: "number", readonly: true },
        { nome: "nome", label: "Nome", tipo: "text", readonly: true },
        { nome: "categoria", label: "Categoria", tipo: "text", readonly: true },
        { nome: "descricao", label: "Descrição", tipo: "textarea", readonly: true },
        { nome: "patrimonio", label: "Patrimônio", tipo: "text", readonly: true },
        { nome: "espaco", label: "Espaço", tipo: "text", readonly: true },
        { nome: "disponivel", label: "Disponível", tipo: "switch", readonly: true },
        { nome: "contatoResponsavel", label: "Contato do Responsável", tipo: "text", readonly: true },
        // { nome: "imagem", label: "Imagem do Equipamento", tipo: "file", readonly: true }
    ];

    return (
        <>
            {itemView && <Modal
                aberto={true}
                titulo="Visualzar Equipamento"
                dadosIniciais={itemView}
                campos={camposView}
                onCancelar={() => actions.view.oncancelar && actions.view.oncancelar(itemView)}
            />}

            {itemEdit && <Modal
                aberto={true}
                titulo="Editar Equipamento"
                dadosIniciais={itemEdit}
                campos={camposEditar}
                onSalvar={() => actions.edit.onsalvar && actions.edit.onsalvar(itemEdit)}
                onCancelar={() => actions.edit.oncancelar && actions.edit.oncancelar(itemEdit)}
                onDeletar={() => actions.edit.ondeletar && actions.edit.ondeletar(itemEdit)}
            />}

            {itemDel && <ConfirmModal 
                aberto={true}
                titulo={`Equipamento "${itemDel.nome}"`}
                mensagem="Deseja deletar esse item permanentemente?"
                onCancelar={() => actions.del.oncancelar && actions.del.oncancelar(itemDel)}
                onConfirmar={() => actions.del.ondeletar && actions.del.ondeletar(itemDel)}
            />}
        
        </>
    )
}