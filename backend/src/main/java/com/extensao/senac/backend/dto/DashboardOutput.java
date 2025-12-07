package com.extensao.senac.backend.dto;

public class DashboardOutput {
    Long qtdReserva;
    Long qtdEspaco;
    Long qtdEquipamento;

    public DashboardOutput(Long qtdReserva, Long qtdEspaco, Long qtdEquipamento) {
        this.qtdReserva = qtdReserva;
        this.qtdEspaco = qtdEspaco;
        this.qtdEquipamento = qtdEquipamento;
    };
    public DashboardOutput(){}

    // Getters e Setters

    public Long getQtdReserva() {
        return this.qtdReserva;
    }

    public void setQtdReserva(Long qtdReserva) {
        this.qtdReserva = qtdReserva;
    }

    public Long getQtdEspaco() {
        return this.qtdEspaco;
    }

    public void setQtdEspaco(Long qtdEspaco) {
        this.qtdEspaco = qtdEspaco;
    }

    public Long getQtdEquipamento() {
        return this.qtdEquipamento;
    }

    public void setQtdEquipamento(Long qtdEquipamento) {
        this.qtdEquipamento = qtdEquipamento;
    }

}
