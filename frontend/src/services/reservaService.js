import { Dashboard } from "../pages/admin/Dashboard";
import { api } from "./api";

export const reservaService = {
  criar: async(data) => api.post("/reserva/criar", data),

  listar: async() => api.get("/reserva/buscar"),

  listarPorEspaco: async(espacoId) =>
    api.get(`/reserva/buscar/espaco/${espacoId}`),

  deletar: async(id) => api.delete(`/reserva/deletar/${id}`),

  atualizar: async(id, data) => api.put(`/reserva/atualizar/${id}`, data),

  buscar: async(id) => api.get(`/reserva/buscar/${id}`),

  disponiveisPorEspaco: async(espacoId) =>
    api.get(`/reserva/disponiveis/espaco/${espacoId}`),

  proximos7dias: async() => api.get('/reserva/proximos7dias'),

  dashboard: async() => api.get('/reserva/dashboard')
};
