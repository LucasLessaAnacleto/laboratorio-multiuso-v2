import './style/status-badge.css';

export function StatusBadge({ status, type = 'status' }) {
  const getStatusConfig = () => {
    switch (status?.toLowerCase()) {
      case 'disponível':
      case 'disponivel':
      case 'ativo':
      case 'success':
        return {
          text: 'Disponível',
          color: '#10B981',
          bgColor: '#D1FAE5',
          className: 'status-disponivel'
        };
      case 'indisponível':
      case 'indisponivel':
      case 'inativo':
      case 'error':
        return {
          text: 'Indisponível',
          color: '#EF4444',
          bgColor: '#FEE2E2',
          className: 'status-indisponivel'
        };
      default:
        return {
          text: status || '---',
          color: '#6B7280',
          bgColor: '#F3F4F6',
          className: 'status-default'
        };
    }
  };

  const config = getStatusConfig();

  return (
    <span 
      className={`status-badge ${config.className}`}
      style={{
        backgroundColor: config.bgColor,
        color: config.color,
        borderColor: config.color
      }}
    >
      {config.text}
    </span>
  );
};

export default StatusBadge;