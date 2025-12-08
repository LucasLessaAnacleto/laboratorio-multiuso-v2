import { useEffect, useState } from "react";
import { anexoService } from "../../services/anexoService";
import { FileInput } from "./FileInput";
import "./style/modal.css"; 

export function Modal({
    aberto,
    titulo = "Modal",
    dadosIniciais = {},
    campos = [],
    onSalvar,
    onCancelar,
    onDeletar,
}) {
    const [form, setForm] = useState(null);
    const [carregamento, setCarregamento] = useState(true);

    useEffect(() => {
        setForm(dadosIniciais);
        setCarregamento(false);
    }, [dadosIniciais, aberto]);

    function alterarCampo(nome, valor) {
        setForm(prev => ({ ...prev, [nome]: valor }));
    }


    if (!aberto) return null;
    if(!carregamento)
    return (
        <div className="modal-overlay">
            <div className="modal-container shadow">
                
                <h2>{titulo}</h2>

                <div className="modal-body">
                    {campos.map((campo, i) => {
                    return (
                        <div className="modal-field" key={i}>
                            <label>{campo.label}</label>

                            {/* TEXT / NUMBER / TEXTAREA */}
                            {(campo.tipo === "text" || campo.tipo === "number") && (
                                <input
                                    type={campo.tipo}
                                    value={form.modal[campo.nome] || ""}
                                    readOnly={campo.readonly}
                                    onChange={e => alterarCampo(campo.nome, e.target.value)}
                                />
                            )}

                            {campo.tipo === "textarea" && (
                                <textarea
                                    value={form.modal[campo.nome] || ""}
                                    readOnly={campo.readonly}
                                    onChange={e => alterarCampo(campo.nome, e.target.value)}
                                />
                            )}

                            {/* SELECT */}
                            {campo.tipo === "select" && (
                                <select
                                    value={form.modal[campo.nome] || ""}
                                    disabled={campo.readonly}
                                    onChange={e => alterarCampo(campo.nome, e.target.value)}
                                >
                                    <option value="" disabled>Selecione...</option>
                                    {campo.opcoes?.map((op, idx) => (
                                        <option key={idx} value={op}>{op}</option>
                                    ))}
                                </select>
                            )}

                            {/* SWITCH */}
                            {campo.tipo === "switch" && (
                                <label className="switch">
                                    <input 
                                        type="checkbox"
                                        checked={!!form.modal[campo.nome]}
                                        disabled={campo.readonly}
                                        onChange={e => alterarCampo(campo.nome, e.target.checked)}
                                    />
                                    <span className="slider"></span>
                                </label>
                            )}

                            {/* FILE INPUT */}
                            {campo.tipo === "file" && (
                                <FileInput
                                    accept={campo.accept}
                                    onChange={async (file) => {
                                        const response = await anexoService.upload(file);
                                        alterarCampo(campo.nome, response?.nome);
                                    }}
                                />
                            )}
                        </div>
                    )})}
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
                        <button className="btn salvar" onClick={() => onSalvar(form)}>
                            Salvar
                        </button>
                    )}
                </div>
            </div>
        </div>
    );
}
