import { api } from "./api";

export const reservaService = {
  criar: (data) => api.post("/reserva/criar", data),

  listar: () => api.get("/reserva/buscar"),

  listarPorEspaco: (espacoId) =>
    api.get(`/reserva/buscar/espaco/${espacoId}`),

  deletar: (id) => api.delete(`/reserva/deletar/${id}`),

  atualizar: (id, data) => api.put(`/reserva/atualizar/${id}`, data),

  buscar: (id) => api.get(`/reserva/buscar/${id}`),

  disponiveisPorEspaco: (espacoId) =>
    api.get(`/reserva/disponiveis/espaco/${espacoId}`),
};
