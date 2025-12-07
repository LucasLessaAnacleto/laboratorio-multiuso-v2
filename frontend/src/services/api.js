import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080",
});

api.interceptors.request.use(config => {
  // const token = localStorage.getItem("token");
  const token = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJjYWRhc3Ryb3MtYXBpIiwic3ViIjoibHVjYXNsZXNzYS5jb20iLCJleHAiOjE3NjUwNzU0MTN9.SxMyuggP-gkDU-e7AdIHXDGqWbFFviORE-I9JWY6M18';
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
    if(error.status == 403){
        localStorage.removeItem("token");
        // Não está autenticado
    }
    return Promise.reject({erro: error.data, status: error.status});
  }
);

export { api };