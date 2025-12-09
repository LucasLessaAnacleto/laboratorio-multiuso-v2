import { FileInput } from "./FileInput";
import { anexoService } from "../../services/anexoService";

export function ModalField({ campo, value, onChange }) {
  //campos = [{ nome: "id", label: "ID", tipo: "number", readonly: true, opcoes: [] }, ...]
  return (
    <div className="modal-field">
      <label>{campo.label}</label>

      {/* TEXT / NUMBER */}
      {(campo.tipo === "text" || campo.tipo === "number") && (
        <input
          type={campo.tipo}
          value={value || ""}
          readOnly={campo.readonly}
          onChange={e => onChange(e.target.value)}
        />
      )}

      {/* TEXTAREA */}
      {campo.tipo === "textarea" && (
        <textarea
          value={value || ""}
          readOnly={campo.readonly}
          onChange={e => onChange(e.target.value)}
        />
      )}

      {/* SELECT */}
      {campo.tipo === "select" && (
        <select
          value={value || ""}
          disabled={campo.readonly}
          onChange={e => onChange(e.target.value)}
        >
          <option value="" disabled>Selecione...</option>
          {campo.opcoes?.map((op, idx) => (
            <option key={idx} 
                title={typeof op === "object" ? op.value : op} 
                value={typeof op === "object" ? op.value : op}>
              {typeof op === "object" ? op.label : op}
            </option>
          ))}
        </select>
      )}

      {/* SWITCH */}
      {campo.tipo === "switch" && (
        <label className="switch">
          <input
            type="checkbox"
            checked={!!value}
            disabled={campo.readonly}
            onChange={e => onChange(e.target.checked)}
          />
          <span className="slider"></span>
        </label>
      )}

      {/* FILE INPUT */}
      {campo.tipo === "file" && (
        <FileInput
          accept={campo.accept}
          onChange={async file => {
            const response = await anexoService.upload(file);
            onChange(response?.nome);
          }}
        />
      )}
    </div>
  );
}
