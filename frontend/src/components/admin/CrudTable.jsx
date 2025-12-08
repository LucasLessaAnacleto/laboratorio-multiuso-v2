import { StatusBadge } from './StatusBudget';
import { ActionButtons } from './ActionButtons';
import { LinkCell } from './LinkCell';
import './style/crud-table.css';

export function CrudTable({ 
  children, 
  dados = [], 
  headers = {},
  columnTypes = {},
  onEdit,
  onDelete,
  onView,
  showPagination = false,
  totalItems = 0,
  currentPage = 1,
  itemsPerPage = 10,
  onPageChange
}) {
  
  // Configuração padrão de tipos de coluna
  const defaultColumnTypes = {
    status: 'status',
    acao: 'action',
    ação: 'action',
    icone: 'icon'
  };

  const types = { ...defaultColumnTypes, ...columnTypes };

  // Renderiza o conteúdo da célula baseado no tipo
  const renderCellContent = (prop, value, item, index) => {
    // console.log(item);
    if (types[prop] === 'status' || prop.toLowerCase().includes('status')) {
      return <StatusBadge status={value} />;
    }
    
    if (types[prop] === 'action' || prop.toLowerCase().includes('acao') || prop.toLowerCase().includes('ação')) {
      return (
        <ActionButtons 
          onEdit={() => onEdit && onEdit(item)}
          onDelete={() => onDelete && onDelete(item)}
          itemId={item.id || index}
        />
      );
    }
    
    if (types[prop] === 'icon' || index === 0) {
      return (
        <div className="icon-cell" onClick={() => onView && onView(item)}>
          <svg width="20" height="20" viewBox="0 0 24 24" fill="none">
            <path d="M1 12C1 12 5 4 12 4C19 4 23 12 23 12C23 12 19 20 12 20C5 20 1 12 1 12Z" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
            <path d="M12 15C13.6569 15 15 13.6569 15 12C15 10.3431 13.6569 9 12 9C10.3431 9 9 10.3431 9 12C9 13.6569 10.3431 15 12 15Z" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
          </svg>
          <span>{value}</span>
        </div>
      );
    }

    if (types[prop] === 'link' || prop.toLowerCase().includes('link')) {
        const url = item?.url || "";
        return (
            <LinkCell url={url}>{value}</LinkCell>
        );
    }
    // por padrão apresenta apenas o conteudo
    return value || "---";
  };
  return (
    <div className="table shadow">
      {children && <h2 className="table-titulo">{children}</h2>}
      <div className="table-wrapper">
        <table>
          <thead>
            <tr>
              {Object.keys(headers).map((prop, i) => (
                <th key={"header-" + i} className={types[prop] || ''}>
                  {headers[prop]}
                </th>
              ))}
            </tr>
          </thead>
          <tbody>
            {dados.map((item, i) => (
              <tr key={"linha-" + i}>
                {Object.keys(headers).map((prop, j) => (
                  <td 
                    key={"coluna-" + i + "-" + j} 
                    className={`${types[prop] || ''} ${prop.toLowerCase().includes('status') ? 'status-column' : ''}`}
                  >
                    {renderCellContent(prop, item.form[prop], item, j)}
                  </td>
                ))}
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      
      {/* Paginação */}
      {showPagination && totalItems > 0 && (
        <div className="table-pagination">
          <span className="pagination-info">
            Mostrando {((currentPage - 1) * itemsPerPage) + 1} a {Math.min(currentPage * itemsPerPage, totalItems)} de {totalItems} resultados
          </span>
          
          <div className="pagination-controls">
            <button 
              className="pagination-btn"
              onClick={() => onPageChange && onPageChange(currentPage - 1)}
              disabled={currentPage === 1}
            >
              Anterior
            </button>
            
            <span className="pagination-current">
              Página {currentPage} de {Math.ceil(totalItems / itemsPerPage)}
            </span>
            
            <button 
              className="pagination-btn"
              onClick={() => onPageChange && onPageChange(currentPage + 1)}
              disabled={currentPage >= Math.ceil(totalItems / itemsPerPage)}
            >
              Próxima
            </button>
          </div>
        </div>
      )}
    </div>
  );
}