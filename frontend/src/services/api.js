import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080",
});

api.interceptors.request.use(config => {
  // const token = localStorage.getItem("token");
  const token = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJjYWRhc3Ryb3MtYXBpIiwic3ViIjoibHVjYXNsZXNzYS5jb20iLCJleHAiOjE3NjUyMTEwMTN9.MJFGskC3SF-Ss7GwIC9cQhwnFYzplr4lH2ikR_btxxo';
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

api.interceptors.response.use(
  (response) => {
    return response.data;
  },
  (error) => {
    if(error.status == 403 || error.code == "ERR_NETWORK"){
        localStorage.removeItem("token");
        document.body.innerHTML += `
                <div id="auth-expired-overlay">
                    <div class="auth-expired-box">
                        <h2>Sessão expirada</h2>
                        <p>Você precisa fazer login novamente para continuar.</p>

                        <button onclick="window.location.href='/login'">
                            Fazer login
                        </button>
                    </div>
                </div>
            `;
    }
    return Promise.reject({erro: error.data, status: error.status});
  }
);

export { api };