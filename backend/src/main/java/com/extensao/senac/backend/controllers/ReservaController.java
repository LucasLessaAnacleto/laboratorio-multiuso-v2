package com.extensao.senac.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.extensao.senac.backend.dto.inputs.ReservaInput;
import com.extensao.senac.backend.modelos.Reserva;
import com.extensao.senac.backend.servicos.ReservaServico;

@Controller
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private ReservaServico reservaServico;

    @GetMapping("/buscar")
    public ResponseEntity<List<Reserva>> buscarTodas() {
        return ResponseEntity.ok(reservaServico.buscarTodos());
    }

    @GetMapping("/buscar/espaco/{espacoId}")
    public ResponseEntity<List<Reserva>> buscarTodasPorEspacoId(@PathVariable String espacoId) {
        return ResponseEntity.ok(reservaServico.buscarTodosPorEspacoId(espacoId));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(reservaServico.buscar(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criarReserva(@RequestBody ReservaInput reservaInput) {
        try {
            return ResponseEntity.ok(reservaServico.criar(reservaInput));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarReserva(@PathVariable Long id, @RequestBody ReservaInput reservaInput) {
        try {
            return ResponseEntity.ok(reservaServico.atualizar(id, reservaInput));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarReserva(@PathVariable Long id) {
        try {
            reservaServico.deletar(id);
            return ResponseEntity.ok("Ok");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
