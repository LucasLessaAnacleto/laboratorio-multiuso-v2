export function ConfirmModal({
    aberto,
    titulo = "Confirmar ação",
    mensagem = "Tem certeza que deseja continuar?",
    onConfirmar,
    onCancelar
}) {
    if (!aberto) return null;

    return (
        <div id="auth-expired-overlay">
            <div className="auth-expired-box">
                <h2>{titulo}</h2>
                <p>{mensagem}</p>

                <div className="confirm-buttons">
                    <button className="cancelar" onClick={onCancelar}>
                        Cancelar
                    </button>

                    <button className="deletar" onClick={onConfirmar}>
                        Deletar
                    </button>
                </div>
            </div>
        </div>
    );
}
