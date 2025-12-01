package com.extensao.senac.backend.dto.inputs;

public class EquipamentoInput {
    private String nome;
    private String categoria;
    private String descricao;
    private String contatoResponsavel;
    private String patrimonio;
    private String anexoImagem;
    private String espacoId;

    // Getters and Setters
    
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCategoria() {
        return this.categoria;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public String getDescricao() {
        return this.descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getContatoResponsavel() {
        return this.contatoResponsavel;
    }
    
    public void setContatoResponsavel(String contatoResponsavel) {
        this.contatoResponsavel = contatoResponsavel;
    }
    
    public String getPatrimonio() {
        return this.patrimonio;
    }
    
    public void setPatrimonio(String patrimonio) {
        this.patrimonio = patrimonio;
    }
    
    public String getAnexoImagem() {
        return this.anexoImagem;
    }
    
    public void setAnexoImagem(String anexoImagem) {
        this.anexoImagem = anexoImagem;
    }
    
    public String getEspacoId() {
        return this.espacoId;
    }
    
    public void setEspacoId(String espacoId) {
        this.espacoId = espacoId;
    }
}

