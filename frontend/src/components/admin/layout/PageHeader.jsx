import "./style/page-header.css";

export function PageHeader({titulo, subtitulo, children}) {
    return (
        <div className="page-header">
            <div className="page-header-container">
                <h1>{titulo}</h1>
                { children || ""}
            </div>
            <div className="page-header-container">
                <h3>{subtitulo || ""}</h3>
            </div>
        </div>
        
    )
}