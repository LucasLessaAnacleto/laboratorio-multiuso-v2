import { api } from "./api";

export const equipamentoService = {
  criar: (data) => api.post("/equipamento/criar", data),

  listar: () => api.get("/equipamento/buscar"),

  deletar: (id) => api.delete(`/equipamento/deletar/${id}`),

  atualizar: (id, data) => api.put(`/equipamento/atualizar/${id}`, data),

  buscar: (id) => api.get(`/equipamento/buscar/${id}`),

  categorias: () => api.get("/equipamento/categorias"),
};
