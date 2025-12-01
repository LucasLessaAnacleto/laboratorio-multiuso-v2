package com.extensao.senac.backend.controllers;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.extensao.senac.backend.modelos.Anexo;
import com.extensao.senac.backend.servicos.AnexoServico;

@Controller
@RequestMapping("/anexo")
public class AnexoController {

    @Autowired
    private AnexoServico anexoServico;

    @PostMapping("/upload/novo")
    public ResponseEntity<String> novoAnexo(@RequestParam("anexo") MultipartFile file){
        try{
            anexoServico.novoAnexo(file);
            return ResponseEntity.ok("Arquivo anexado com sucesso!");
        }catch(RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch(IOException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/download/{nomeAnexo}")
    public ResponseEntity<byte[]> downloadAnexo(@PathVariable String nomeAnexo){
        try {
            Anexo anexo = anexoServico.buscarAnexo(nomeAnexo);
            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, anexo.getContentType())
                .header(HttpHeaders.CONTENT_LENGTH, anexo.getSize().toString())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+anexo.getNomeAnexo()+"\"")
                .body(anexo.getContentBlob());
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
