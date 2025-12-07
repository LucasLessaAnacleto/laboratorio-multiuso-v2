import { api } from "./api";

const authService = {
  login: async ({ email, senha }) => {
    const dadoUsuario = await api.post("/usuarios/login", { email, senha });
    localStorage.setItem("token", dadoUsuario.token);
    return dadoUsuario;
  },

  registrar: async ({ email, nome, senha }) => {
    const dadoUsuario = await api.post("/usuarios/criar", { 
        email,
        nome,
        senha
    });
    return dadoUsuario;
  },

  logout: () => {
    localStorage.removeItem("token");
  },

  getUsuario: () => {
    return api.get("/usuarios/me");
  }
};

export { authService };