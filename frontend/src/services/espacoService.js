import { api } from "./api";

export const espacoService = {
  criar: (data) => api.post("/espaco/criar", data),

  listar: (pesquisa = "") =>
    api.get("/espaco/buscar", {
      params: pesquisa ? { pesquisa } : {},
    }),

  deletar: (id) => api.delete(`/espaco/deletar/${id}`),

  atualizar: (id, data) => api.put(`/espaco/atualizar/${id}`, data),

  buscar: (id) => api.get(`/espaco/buscar/${id}`),
};
