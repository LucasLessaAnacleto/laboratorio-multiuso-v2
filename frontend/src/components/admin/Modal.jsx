import { useEffect, useState } from "react";
import { anexoService } from "../../services/anexoService";
import { FileInput } from "./FileInput";
import "./style/modal.css"; 
import { ModalField } from "./ModalField";

export function Modal({
    aberto,
    titulo = "Modal",
    dadosIniciais = {},
    campos = [],
    onSalvar,
    onCancelar,
    onDeletar,
}) {
    const [formModal, setFormModal] = useState({}); // dados editáveis do modal

    const [carregamento, setCarregamento] = useState(true);

    useEffect(() => {
        if (aberto) {
            setFormModal(dadosIniciais);
            setCarregamento(false);
        }
    }, [aberto, dadosIniciais]);


    function alterarCampo(nome, valor) {
        setFormModal(prev => ({ ...prev, [nome]: valor }));
    }

    useEffect(() => {
        console.log('formModal',formModal)
    },[formModal])


    if (!aberto) return null;
    if(!carregamento)
    return (
        <div className="modal-overlay">
            <div className="modal-container shadow">
                
                <h2>{titulo}</h2>

                <div className="modal-body">
                    {campos.map((campo, i) => (
                        <ModalField
                        key={campo.nome || i}
                        campo={campo}
                        value={formModal[campo.nome]}
                        onChange={valor => alterarCampo(campo.nome, valor)}
                        />
                    ))}
                </div>

                <div className="modal-footer">
                    {onCancelar && (
                        <button className="btn cancelar" onClick={onCancelar}>
                            Cancelar
                        </button>
                    )}

                    {onDeletar && (
                        <button className="btn deletar" onClick={onDeletar}>
                            Deletar
                        </button>
                    )}

                    {onSalvar && (
                        <button className="btn salvar" onClick={() => onSalvar(formModal)}>
                            Salvar
                        </button>
                    )}
                </div>
            </div>
        </div>
    );
}
