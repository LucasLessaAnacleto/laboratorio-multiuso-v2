package com.extensao.senac.backend.dto.inputs;

public class EspacoInput {

    private String nome;

    private String descricao;

    private String endereco; 

    private Boolean disponivel;

    private String departamento;

    private String politicaUso;

    private String sala;

    private String andar;

    private String nomeImagemCapa;

    private String contato;

    // GETTERS E SETTERS


    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Boolean getDisponivel() {
        return this.disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getDepartamento() {
        return this.departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getPoliticaUso() {
        return this.politicaUso;
    }

    public void setPoliticaUso(String politicaUso) {
        this.politicaUso = politicaUso;
    }

    public String getSala() {
        return this.sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getAndar() {
        return this.andar;
    }

    public void setAndar(String andar) {
        this.andar = andar;
    }

    public String getNomeImagemCapa() {
        return this.nomeImagemCapa;
    }

    public void setNomeImagemCapa(String nomeImagemCapa) {
        this.nomeImagemCapa = nomeImagemCapa;
    }

    public String getContato() {
        return this.contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
   
}
