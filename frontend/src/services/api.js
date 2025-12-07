import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080",
});

api.interceptors.request.use(config => {
  // const token = localStorage.getItem("token");
  const token = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJjYWRhc3Ryb3MtYXBpIiwic3ViIjoic2lzdGVtYS50ZXN0ZUB0ZXN0ZS5jb20iLCJleHAiOjE3NjUxNTI5NjJ9.hPlKibXBSHb3D6dH7ka3ycumn96KO58guIZvqOeZwp4';
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