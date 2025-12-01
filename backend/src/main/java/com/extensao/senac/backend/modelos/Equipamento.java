package com.extensao.senac.backend.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private String descricao;

    @Column
    private String contatoResponsavel;

    @Column(unique = true, nullable = false)
    private String patrimonio;

    @Column
    private String categoria;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anexo_imagem", nullable = true)
    private Anexo imagem;

    @ManyToOne
    @JoinColumn(name = "espaco_id", nullable = false)
    private Espaco espaco;

    @Column
    private boolean disponivel = true;

    // Getters and Setters


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Anexo getImagem() {
        return this.imagem;
    }

    public void setImagem(Anexo imagem) {
        this.imagem = imagem;
    }

    public Espaco getEspaco() {
        return this.espaco;
    }

    public void setEspaco(Espaco espaco) {
        this.espaco = espaco;
    }

    public boolean isDisponivel() {
        return this.disponivel;
    }

    public boolean getDisponivel() {
        return this.disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

}
