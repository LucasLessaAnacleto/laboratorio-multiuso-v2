package com.extensao.senac.backend.modelos;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String descricaoReserva;

    @Column
    private LocalDateTime dataReserva;

    @Column
    private Long duracao;

    @Column
    private LocalDateTime dataSolicitacao = LocalDateTime.now();

    @Column
    private LocalDateTime dataCancelamento;

    @ManyToOne
    @JoinColumn(name = "espaco_id")
    private Espaco espaco;

    // Getters and Setters
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getDescricaoReserva() {
        return this.descricaoReserva;
    }
    
    public void setDescricaoReserva(String descricaoReserva) {
        this.descricaoReserva = descricaoReserva;
    }
    
    public LocalDateTime getDataReserva() {
        return this.dataReserva;
    }
    
    public void setDataReserva(LocalDateTime dataReserva) {
        this.dataReserva = dataReserva;
    }
    
    public Long getDuracao() {
        return this.duracao;
    }
    
    public void setDuracao(Long duracao) {
        this.duracao = duracao;
    }
    
    public LocalDateTime getDataSolicitacao() {
        return this.dataSolicitacao;
    }
    
    public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }
    
    public Espaco getEspaco() {
        return this.espaco;
    }
    
    public void setEspaco(Espaco espaco) {
        this.espaco = espaco;
    }

    public LocalDateTime getDataCancelamento() {
        return this.dataCancelamento;
    }

    public void setDataCancelamento(LocalDateTime dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }
}

