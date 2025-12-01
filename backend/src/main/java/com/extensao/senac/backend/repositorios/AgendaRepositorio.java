package com.extensao.senac.backend.repositorios;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.extensao.senac.backend.modelos.view.Agenda;

@Repository
public interface AgendaRepositorio extends JpaRepository<Agenda, String> {
    List<Agenda> findAllByEspacoId(String espacoId);
    List<Agenda> findAllByEspacoIdAndDtGreaterThan(String espacoId, LocalDateTime dt);
}
