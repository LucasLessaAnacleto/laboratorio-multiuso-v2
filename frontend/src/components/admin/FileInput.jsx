import { useRef, useState } from "react";

export function FileInput({ label, onChange, accept }) {
  const [fileName, setFileName] = useState("");
  const inputRef = useRef(null);
  function handleFile(e) {
    const file = e.target.files[0];
    if (file) {
      setFileName(file.name);
      onChange(file); // envia arquivo para o pai
    }
  }

  return (
    <div className={`file-input ${fileName ? "file-input-has-file" : ""}`}>
      <div className="file-input-custom" onClick={() => inputRef.current.click()}>
        {/* Ícone de upload - você pode substituir por uma imagem ou ícone de sua biblioteca */}
        <i>📁</i>
        <span>{fileName ? "Alterar arquivo" : "Escolher arquivo"}</span>
        <input
          type="file"
          ref={inputRef}
          accept={accept}
          onChange={handleFile}
          className="file-input-multipart"
        />
      </div>

      {fileName && (
        <span className="file-input-descricao">
          Arquivo selecionado: <b>{fileName}</b>
        </span>
      )}
    </div>
  );
}