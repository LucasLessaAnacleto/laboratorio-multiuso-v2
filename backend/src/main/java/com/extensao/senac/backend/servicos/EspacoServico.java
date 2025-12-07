package com.extensao.senac.backend.servicos;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extensao.senac.backend.dto.inputs.EspacoInput;
import com.extensao.senac.backend.modelos.Anexo;
import com.extensao.senac.backend.modelos.Espaco;
import com.extensao.senac.backend.repositorios.EspacoRepositorio;

@Service
public class EspacoServico {
    
    @Autowired
    private EspacoRepositorio espacoRepositorio;

    @Autowired
    private AnexoServico anexoServico;

    public Espaco criar(EspacoInput espacoInput) throws RuntimeException {
        Espaco espaco = mapeiaEspacoInput(espacoInput);
        if(espaco.getNome() == null || espaco.getNome().isEmpty()){
            throw new RuntimeException("O nome do espaço é obrigatório.");
        }
        if(espaco.getDescricao() == null || espaco.getDescricao().isEmpty()){
            throw new RuntimeException("A descrição do espaço é obrigatória.");
        }
        if(espaco.getEndereco() == null || espaco.getEndereco().isEmpty()){
            throw new RuntimeException("O endereço do espaço é obrigatório.");
        }
        if(espaco.getDepartamento() == null || espaco.getDepartamento().isEmpty()){
            throw new RuntimeException("O departamento do espaço é obrigatório.");
        }
        // if(espaco.getDisponibilidade() == null || espaco.getDisponibilidade().isEmpty()){
        //     throw new RuntimeException("A disponibilidade do espaço é obrigatória.");
        // }
        // if(espaco.getPoliticaUso() == null || espaco.getPoliticaUso().isEmpty()){
        //     throw new RuntimeException("A política de uso do espaço é obrigatória.");
        // }
        // if(espaco.getSala() == null || espaco.getSala().isEmpty()){
        //     throw new RuntimeException("A sala do espaço é obrigatória.");
        // }
        // if(espaco.getAndar() == null || espaco.getAndar().isEmpty()){
        //     throw new RuntimeException("O andar do espaço é obrigatório.");
        // }
        if(espaco.getContato() == null || espaco.getContato().isEmpty()){
            throw new RuntimeException("O contato do espaço é obrigatório.");
        }
        if(espacoRepositorio.existsByNome(espaco.getNome())){
            throw new RuntimeException("Já existe um espaço cadastrado com esse nome.");
        }
        if(espacoInput.getNomeImagemCapa() != null && !espacoInput.getNomeImagemCapa().isEmpty()){
            Anexo imagemCapa = anexoServico.buscarAnexo(espacoInput.getNomeImagemCapa());
            espaco.setImagemCapa(imagemCapa);
        }
        return espacoRepositorio.save(espaco);
    }

    public List<Espaco> buscarTodos(String pesquisa) {
        if (pesquisa == null || pesquisa.isEmpty()) {
            return espacoRepositorio.findAll();
        } else {
            return buscarFiltro(pesquisa);
        }
    }

    public Espaco buscar(String id) throws RuntimeException {
        Optional<Espaco> espaco = espacoRepositorio.findById(id);
        if (espaco.isPresent()) {
            return espaco.get();
        } else {
            throw new RuntimeException("Espaço não encontrado com o ID " + id);
        }
    }

    public Espaco atualizar(String id, EspacoInput espacoInput) throws RuntimeException {
        try {
            Espaco espacoResult = this.buscar(id);
            espacoResult.setNome(espacoInput.getNome() != null ? espacoInput.getNome() : espacoResult.getNome());
            espacoResult.setDescricao(espacoInput.getDescricao() != null ? espacoInput.getDescricao() : espacoResult.getDescricao());
            espacoResult.setEndereco(espacoInput.getEndereco() != null ? espacoInput.getEndereco() : espacoResult.getEndereco());
            espacoResult.setPoliticaUso(espacoInput.getPoliticaUso() != null ? espacoInput.getPoliticaUso() : espacoResult.getPoliticaUso());
            espacoResult.setSala(espacoInput.getSala() != null ? espacoInput.getSala() : espacoResult.getSala());
            espacoResult.setAndar(espacoInput.getAndar() != null ? espacoInput.getAndar() : espacoResult.getAndar());
            espacoResult.setDisponivel(espacoInput.getDisponivel() != null ? espacoInput.getDisponivel() : espacoResult.getDisponivel());
            espacoResult.setDepartamento(espacoInput.getDepartamento() != null ? espacoInput.getDepartamento() : espacoResult.getDepartamento());
            espacoResult.setContato(espacoInput.getContato() != null ? espacoInput.getContato() : espacoResult.getContato());
    
            if(espacoInput.getNomeImagemCapa() != null && !espacoInput.getNomeImagemCapa().isEmpty()){
            Anexo imagemCapa = anexoServico.buscarAnexo(espacoInput.getNomeImagemCapa());
                espacoResult.setImagemCapa(imagemCapa);
            }    
            return espacoRepositorio.save(espacoResult);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deletar(String id)  {
        if(!espacoRepositorio.existsById(id)){
            throw new RuntimeException("Espaço não encontrado com o ID " + id);
        }
        espacoRepositorio.deleteById(id);
    }

    // Private

    private List<Espaco> buscarFiltro(String pesquisa) {
        String pesq = pesquisa.toLowerCase();
        List<Espaco> espacosFiltrados = espacoRepositorio.findAll().stream()
            .filter((espaco) ->  Objects.requireNonNullElse(espaco.getNome(), "").toLowerCase().contains(pesq) ||
                   Objects.requireNonNullElse(espaco.getDescricao(), "").toLowerCase().contains(pesq) ||
                   Objects.requireNonNullElse(espaco.getDepartamento(), "").toLowerCase().contains(pesq) ||
                   Objects.requireNonNullElse(espaco.getEndereco(), "").toLowerCase().contains(pesq)
            ).toList();
        return espacosFiltrados;
    }

    private Espaco mapeiaEspacoInput(EspacoInput espacoInput) {
        Espaco espaco = new Espaco();
        espaco.setNome(espacoInput.getNome());  
        espaco.setDescricao(espacoInput.getDescricao());
        espaco.setEndereco(espacoInput.getEndereco());
        espaco.setPoliticaUso(espacoInput.getPoliticaUso());
        espaco.setSala(espacoInput.getSala());
        espaco.setAndar(espacoInput.getAndar());
        espaco.setDisponivel(espacoInput.getDisponivel());
        espaco.setDepartamento(espacoInput.getDepartamento());
        espaco.setContato(espacoInput.getContato());
        return espaco;
    }
}
