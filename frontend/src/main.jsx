import { BrowserRouter } from "react-router-dom";
import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { App } from './App.jsx'
import { UsuarioProvider } from './contexts/UsuarioProvider.jsx';
import './index.css'
import { LoadingProvider } from "./contexts/LoadingProvider.jsx";

createRoot(document.getElementById('root')).render(
  // <StrictMode>
    <BrowserRouter>
      <UsuarioProvider>
        <LoadingProvider>
          <App />
        </LoadingProvider>
      </UsuarioProvider>
    </BrowserRouter>
  // </StrictMode>
  ,
)
