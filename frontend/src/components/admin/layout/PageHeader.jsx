import "./style/page-header.css";

export function PageHeader({titulo, subtitulo, children}) {
    console.log("PAGE HEADER");
    return (
        <div className="page-header">
            <h1>{titulo}</h1>
            <div className="page-header-container">
                <h3>{subtitulo || ""}</h3>
                { children || ""}
            </div>
        </div>
        
    )
}