import './style/link-cell.css';

export const LinkCell = ({ url, children }) => {
  return (
    <a href={url} className="link-cell" target="_blank" rel="noopener noreferrer">
      {children}
    </a>
  );
};