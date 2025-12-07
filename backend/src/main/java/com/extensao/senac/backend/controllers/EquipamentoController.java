package com.extensao.senac.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.extensao.senac.backend.dto.inputs.EquipamentoInput;
import com.extensao.senac.backend.modelos.Equipamento;
import com.extensao.senac.backend.servicos.EquipamentoServico;

@Controller
@RequestMapping("/equipamento")
@CrossOrigin(origins = "*")
public class EquipamentoController {

    @Autowired
    private EquipamentoServico equipamentoServico;

    @GetMapping("/buscar")
    public ResponseEntity<List<Equipamento>> listarEquipamentos() {
        return ResponseEntity.ok(equipamentoServico.buscarTodos());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarEquipamento(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(equipamentoServico.buscar(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<String>> listarCategorias() {
        return ResponseEntity.ok(equipamentoServico.listarCategorias());
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criarEquipamento(@RequestBody EquipamentoInput equipamentoInput) {
        try {
            return ResponseEntity.ok(equipamentoServico.criarEquipamento(equipamentoInput));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarEquipamento(@PathVariable Long id, @RequestBody EquipamentoInput equipamentoInput) {
        try {
            return ResponseEntity.ok(equipamentoServico.atualizarEquipamento(id, equipamentoInput));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarEquipamento(@PathVariable Long id) {
        try {
            equipamentoServico.deletar(id);
            return ResponseEntity.ok().body("Ok");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
