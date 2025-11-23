package com.extensao.senac.backend.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extensao.senac.backend.modelos.Espaco;
import com.extensao.senac.backend.repositorios.EspacoRepositorio;

@Service
public class EspacoServico {
    
    @Autowired
    private EspacoRepositorio espacoRepositorio;

    public Espaco criar(){
        
    }
}
