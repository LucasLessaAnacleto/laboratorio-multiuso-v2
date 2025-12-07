import { api } from "./api";

export const agendaService = {
  listar: (espacoId) => api.get(`/agenda/${espacoId}`),

  disponiveis: (espacoId) => api.get(`/agenda/${espacoId}/disponiveis`),
};
