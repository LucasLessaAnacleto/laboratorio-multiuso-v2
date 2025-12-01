package com.extensao.senac.backend.modelos.view;

import java.time.LocalDateTime;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Immutable      
@Table(name = "agenda_mestre")
public class Agenda {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "dt")
    private LocalDateTime dt;

    @Column(name = "ano")
    private Integer ano;

    @Column(name = "mes")
    private Integer mes;

    @Column(name = "dia")
    private Integer dia;

    @Column(name = "dia_semana")
    private Integer diaSemana;

    @Column(name = "hora")
    private Integer hora;

    @Column(name = "espaco_id")
    private String espacoId;

    @Column(name = "reserva_id")
    private Long reservaId;

    // Getters and Setters

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public LocalDateTime getDt() {
        return this.dt;
    }
    
    public void setDt(LocalDateTime dt) {
        this.dt = dt;
    }
    
    public Integer getAno() {
        return this.ano;
    }
    
    public void setAno(Integer ano) {
        this.ano = ano;
    }
    
    public Integer getMes() {
        return this.mes;
    }
    
    public void setMes(Integer mes) {
        this.mes = mes;
    }
    
    public Integer getDia() {
        return this.dia;
    }
    
    public void setDia(Integer dia) {
        this.dia = dia;
    }
    
    public Integer getDiaSemana() {
        return this.diaSemana;
    }
    
    public void setDiaSemana(Integer diaSemana) {
        this.diaSemana = diaSemana;
    }
    
    public Integer getHora() {
        return this.hora;
    }
    
    public void setHora(Integer hora) {
        this.hora = hora;
    }
    
    public String getEspacoId() {
        return this.espacoId;
    }
    
    public void setEspacoId(String espacoId) {
        this.espacoId = espacoId;
    }
    
    public Long getReservaId() {
        return this.reservaId;
    }
    
    public void setReservaId(Long reservaId) {
        this.reservaId = reservaId;
    }
    
}

