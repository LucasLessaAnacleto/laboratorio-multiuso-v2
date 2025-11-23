package com.extensao.senac.backend.api.dto.input;

public class CadastroEspaco {

    private String nome;

    private String descricao;

    private String endereco; 

    private String disponibilidade;

    private String departamento;

    private String politicaUso;

    private String sala;

    private String andar;

    private String imagemCapa;

    // GETTERS E SETTERS

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getPoliticaUso() {
        return politicaUso;
    }

    public void setPoliticaUso(String politicaUso) {
        this.politicaUso = politicaUso;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getAndar() {
        return andar;
    }

    public void setAndar(String andar) {
        this.andar = andar;
    }
}
