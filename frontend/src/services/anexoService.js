import { api } from "./api";

export const anexoService = {
  upload: (file) => {
    const formData = new FormData();
    formData.append("anexo", file);

    return api.post("/anexo/upload/novo", formData, {
      headers: { "Content-Type": "multipart/form-data" },
    });
  },

  download: (fileName) => {
    return api.get(`/anexo/download/${fileName}`, {
      responseType: "blob",
    });
  },
};
