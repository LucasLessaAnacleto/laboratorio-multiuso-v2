package com.extensao.senac.backend.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extensao.senac.backend.dto.inputs.EquipamentoInput;
import com.extensao.senac.backend.modelos.Anexo;
import com.extensao.senac.backend.modelos.Equipamento;
import com.extensao.senac.backend.modelos.Espaco;
import com.extensao.senac.backend.repositorios.EquipamentoRepositorio;

@Service
public class EquipamentoServico {

    @Autowired
    private EquipamentoRepositorio equipamentoRepositorio;

    @Autowired
    private AnexoServico anexoServico;

    @Autowired
    private EspacoServico espacoServico;

    public List<Equipamento> buscarTodos() {
        return equipamentoRepositorio.findAll();
    }

    public Equipamento buscar(Long id) {
        Optional<Equipamento> equipamentoOpt = equipamentoRepositorio.findById(id);
        if (equipamentoOpt.isPresent()) {
            return equipamentoOpt.get();
        } else {
            throw new RuntimeException("Equipamento não encontrado.");
        }
    }

    public List<String> listarCategorias() {
        return equipamentoRepositorio.findDistinctCategorias();
    }

    public Equipamento criarEquipamento(EquipamentoInput equipamentoInput) {
        Equipamento equipamento = this.mepeiaEquipamentoInput(equipamentoInput);

        if(equipamento.getNome() == null || equipamento.getNome().isEmpty()){
            throw new RuntimeException("O nome do equipamento é obrigatório.");
        }
        if(equipamento.getCategoria() == null || equipamento.getCategoria().isEmpty()){
            throw new RuntimeException("A categoria do equipamento é obrigatória.");
        }
        // if(equipamento.getDescricao() == null || equipamento.getDescricao().isEmpty()){
        //     throw new RuntimeException("A descrição do equipamento é obrigatória.");
        // }
        // if(equipamento.getContatoResponsavel() == null || equipamento.getContatoResponsavel().isEmpty()){
        //     throw new RuntimeException("O contato do responsável pelo equipamento é obrigatório.");
        // }
        if(equipamento.getPatrimonio() == null || equipamento.getPatrimonio().isEmpty()){
            throw new RuntimeException("O patrimônio do equipamento é obrigatório.");
        }
        Espaco espaco = espacoServico.buscar(equipamentoInput.getEspacoId());
        equipamento.setEspaco(espaco);        
        if(equipamentoInput.getAnexoImagem() != null && !equipamentoInput.getAnexoImagem().isEmpty()){
            try {
                Anexo anexoImagem = anexoServico.buscarAnexo(equipamentoInput.getAnexoImagem());
                equipamento.setImagem(anexoImagem);
            } catch (RuntimeException e) {
                throw new RuntimeException("Anexo de imagem não encontrado.");
            }
        }
        return equipamentoRepositorio.save(equipamento);
    }

    public Equipamento atualizarEquipamento(Long id, EquipamentoInput equipamentoInput) {
        try {
            Equipamento equipamentoResult = this.buscar(id);
    
            Equipamento equipamentoAtualizado = this.mepeiaEquipamentoInput(equipamentoInput);
            equipamentoResult.setNome(equipamentoAtualizado.getNome() != null ? equipamentoAtualizado.getNome() : equipamentoResult.getNome());
            equipamentoResult.setCategoria(equipamentoAtualizado.getCategoria() != null ? equipamentoAtualizado.getCategoria() : equipamentoResult.getCategoria());
            equipamentoResult.setDescricao(equipamentoAtualizado.getDescricao() != null ? equipamentoAtualizado.getDescricao() : equipamentoResult.getDescricao());
            equipamentoResult.setContatoResponsavel(equipamentoAtualizado.getContatoResponsavel() != null ? equipamentoAtualizado.getContatoResponsavel() : equipamentoResult.getContatoResponsavel());
            equipamentoResult.setPatrimonio(equipamentoAtualizado.getPatrimonio() != null ? equipamentoAtualizado.getPatrimonio() : equipamentoResult.getPatrimonio());
            equipamentoResult.setDisponivel(equipamentoAtualizado.getDisponivel() != null ? equipamentoAtualizado.getDisponivel() : equipamentoResult.getDisponivel());
    
            if(equipamentoInput.getAnexoImagem() != null && !equipamentoInput.getAnexoImagem().isEmpty()){
                try {
                    Anexo anexoImagem = anexoServico.buscarAnexo(equipamentoInput.getAnexoImagem());
                    equipamentoResult.setImagem(anexoImagem);
                } catch (RuntimeException e) {
                    throw new RuntimeException("Anexo de imagem não encontrado.");
                }
            }

            return equipamentoRepositorio.save(equipamentoResult);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deletar(Long id) {
        if(!equipamentoRepositorio.existsById(id)){
            throw new RuntimeException("Equipamento não encontrado.");
        }
        equipamentoRepositorio.deleteById(id);
    }
    // private
    private Equipamento mepeiaEquipamentoInput(EquipamentoInput equipamentoInput) {
        Equipamento equipamento = new Equipamento();
        equipamento.setNome(equipamentoInput.getNome());
        equipamento.setCategoria(equipamentoInput.getCategoria());
        equipamento.setDescricao(equipamentoInput.getDescricao());
        equipamento.setContatoResponsavel(equipamentoInput.getContatoResponsavel());
        equipamento.setPatrimonio(equipamentoInput.getPatrimonio());
        equipamento.setDisponivel(equipamentoInput.getDisponivel());
        return equipamento;
    }
}
