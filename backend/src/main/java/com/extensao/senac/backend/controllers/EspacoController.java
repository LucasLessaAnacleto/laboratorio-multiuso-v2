package com.extensao.senac.backend.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.extensao.senac.backend.dto.inputs.EspacoInput;
import com.extensao.senac.backend.modelos.Espaco;
import com.extensao.senac.backend.servicos.EspacoServico;

@Controller
@RequestMapping("/espaco")
public class EspacoController {
    @Autowired
    private EspacoServico espacoServico;

    @PostMapping("/criar")
    public ResponseEntity<?> criarEspaco(@RequestBody EspacoInput espacoInput) {
        try{
            return ResponseEntity.ok(espacoServico.criar(espacoInput));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao criar espaço: " + e.getMessage());
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Espaco>> buscarEspaco(@RequestParam(required = false) String pesquisa) {
        return ResponseEntity.ok(espacoServico.buscarTodos(pesquisa));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Espaco> buscarEspacoPorId(@PathVariable String id) {
        try {
            Espaco espaco = espacoServico.buscar(id);
            return ResponseEntity.ok(espaco);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarEspaco(@PathVariable String id, @RequestBody EspacoInput espacoInput) {
        try {
            Espaco espacoAtualizado = espacoServico.atualizar(id, espacoInput);
            return ResponseEntity.ok(espacoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar espaço: " + e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarEspaco(@PathVariable String id) {
        try {
            espacoServico.deletar(id);
            return ResponseEntity.ok("OK");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao deletar espaço: " + e.getMessage());
        }
    }
}
