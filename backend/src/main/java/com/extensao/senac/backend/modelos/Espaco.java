package com.extensao.senac.backend.modelos;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Espaco {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String nome;

    @Column
    private String descricao;

    @Column
    private String endereco;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imagem_capa", nullable = true)
    private Anexo imagemCapa;

    @Column
    private Boolean disponivel = true;

    @Column
    private boolean ativo = true;

    @Column
    private String departamento;

    @Column
    private String politicaUso;

    @Column
    private String sala;

    @Column
    private String andar;

    @Column
    private String contato;

    @OneToMany(mappedBy = "espaco")
    private List<Equipamento> equipamentos;

    @OneToMany(mappedBy = "espaco")
    private List<Reserva> reservas;

    // Getters and Setters

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
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

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Anexo getImagemCapa() {
        return this.imagemCapa;
    }

    public void setImagemCapa(Anexo imagemCapa) {
        this.imagemCapa = imagemCapa;
    }

    public Boolean getDisponivel() {
        return this.disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        if(disponivel != null){
            this.disponivel = disponivel;
        }
    }

    public boolean isAtivo() {
        return this.ativo;
    }

    public boolean getAtivo() {
        return this.ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
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

    public String getContato() {
        return this.contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

}
