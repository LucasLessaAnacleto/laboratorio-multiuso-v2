package com.extensao.senac.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.extensao.senac.backend.modelos.view.Agenda;
import com.extensao.senac.backend.servicos.AgendaServico;

@Controller
@RequestMapping("/agenda")
@CrossOrigin(origins = "*")
public class AgendaController {

    @Autowired
    private AgendaServico agendaServico;

    @GetMapping("/{espacoId}/disponiveis")
    public ResponseEntity<List<Agenda>> listarDisponiveis(@PathVariable String espacoId) {
        return ResponseEntity.ok(agendaServico.buscarDisponiveis(espacoId));
    }

    @GetMapping("/{espacoId}")
    public ResponseEntity<List<Agenda>> listarTodos(@PathVariable String espacoId) {
        return ResponseEntity.ok(agendaServico.buscarTodos(espacoId));
    }
}