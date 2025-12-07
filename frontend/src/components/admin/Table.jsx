import "./style/table.css";

export function Table({ children, dados=[], headers={} }){
    return (
        <div className="table shadow">
            <h2 className="table-titulo">{children}</h2>
            <div className="table-wrapper">
                <table>
                <thead>
                    <tr>
                    {Object.keys(headers).map((prop, i) => (
                        <th key={"header-"+i}>{headers[prop]}</th>
                    ))}
                    </tr>
                </thead>
                <tbody>
                    {dados.map((item, i) => (
                        <tr key={"linha-"+i}>
                            {Object.keys(headers).map((prop, j) => (
                                <td key={"coluna-"+i+"-"+j}>
                                    {item[prop] || "---"}
                                </td>
                            ))}
                        </tr>
                    ))}
                </tbody>
                </table>
            </div>
        </div>
    );
}