package com.extensao.senac.backend.servicos;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extensao.senac.backend.modelos.view.Agenda;
import com.extensao.senac.backend.repositorios.AgendaRepositorio;

@Service
public class AgendaServico {
    @Autowired
    private AgendaRepositorio agendaRepositorio;

    public List<Agenda> buscarTodos(String espacoId) {
        return agendaRepositorio.findAllByEspacoId(espacoId);
    }

    public List<Agenda> buscarDisponiveis(String espacoId) {
        return agendaRepositorio.findAllByEspacoIdAndDtGreaterThan(espacoId, LocalDateTime.now());
    }

}
