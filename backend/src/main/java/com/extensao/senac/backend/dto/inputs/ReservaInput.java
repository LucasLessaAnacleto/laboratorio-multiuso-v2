package com.extensao.senac.backend.dto.inputs;

import java.time.LocalDateTime;

public class ReservaInput {
    private String descricaoReserva;
    private LocalDateTime dataReserva;
    private Long duracao;
    private String espacoId;
    private LocalDateTime dataCancelamento;

    // Getters and Setters

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

    public String getEspacoId() {
        return this.espacoId;
    }

    public void setEspacoId(String espacoId) {
        this.espacoId = espacoId;
    }

    public LocalDateTime getDataCancelamento() {
        return this.dataCancelamento;
    }

    public void setDataCancelamento(LocalDateTime dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }
  
}
