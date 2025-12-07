package com.extensao.senac.backend.dto.inputs;

public class UsuarioAtualizadoInput {
    private String nome;
    private String fotoPerfil;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFotoPerfil() {
        return this.fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
}
