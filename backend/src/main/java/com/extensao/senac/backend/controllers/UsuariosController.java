package com.extensao.senac.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.extensao.senac.backend.dto.inputs.UsuarioInput;
import com.extensao.senac.backend.servicos.UsuarioService;

@Controller
@RequestMapping("/usuarios")
@CrossOrigin
public class UsuariosController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody UsuarioInput usuario) {
        try {
            return ResponseEntity
                    .created(null)
                    .body(usuarioService.cadastrarUsuario(usuario));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioInput usuario) {
        try {
            return ResponseEntity.ok(usuarioService.loginUsuario(usuario));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(e.getMessage());
        }
    }
}
