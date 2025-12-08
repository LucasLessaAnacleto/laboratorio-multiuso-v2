package com.extensao.senac.backend.servicos;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.extensao.senac.backend.modelos.Anexo;
import com.extensao.senac.backend.repositorios.AnexoRepositorio;

@Service
public class AnexoServico {

    @Autowired
    private AnexoRepositorio anexoRepositorio;

    public Anexo novoAnexo(MultipartFile file) throws RuntimeException, IOException{
        String nomeAnexo = file.getOriginalFilename();
        if(nomeAnexo.lastIndexOf('.') <= 0 ){
            throw new RuntimeException("O arquivo deve ter uma extensão!");
        }
        if(file.getSize() > 10_000_000){
            throw new RuntimeException("Limite de tamanho de arquivo excedido! Máximo: 10mb");
        }
        // if(anexoRepositorio.existsById(nomeAnexo)){
        //     throw new RuntimeException("Arquivo "+nomeAnexo+" já existe!");
        // }
        Anexo anexo = new Anexo();
        anexo.setNomeAnexo(nomeAnexo);
        anexo.setSize(file.getSize());
        anexo.setContentType(file.getContentType());
        try {
            anexo.setContentBlob(file.getBytes());
        } catch(IOException e) {
            throw new RuntimeException("Não foi possível anexar arquivo!");
        }
        anexoRepositorio.save(anexo);
        return anexo;
    }

    public Anexo buscarAnexo(String nomeAnexo) throws RuntimeException {
        Optional<Anexo> anexoResult = anexoRepositorio.findById(nomeAnexo);
        if(anexoResult.isEmpty()){
            throw new RuntimeException("Arquivo não disponível para download!");
        }
        return anexoResult.get();
    }
}
