package com.extensao.senac.backend.servicos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extensao.senac.backend.dto.inputs.ReservaInput;
import com.extensao.senac.backend.modelos.Espaco;
import com.extensao.senac.backend.modelos.Reserva;
import com.extensao.senac.backend.repositorios.ReservaRepositorio;

@Service
public class ReservaServico {
    @Autowired
    private ReservaRepositorio reservaRepositorio;

    @Autowired
    private EspacoServico espacoServico;

    public List<Reserva> buscarTodos() {
        return reservaRepositorio.findAll();
    }
    
    public List<Reserva> buscarTodosPorEspacoId(String espacoId) {
        return reservaRepositorio.findAllByEspacoId(espacoId);
    }

    public Reserva buscar(Long id) throws RuntimeException {
        Optional<Reserva> reserva = reservaRepositorio.findById(id);
        if (reserva.isPresent()) {
            return reserva.get();
        } else {
            throw new RuntimeException("Reserva não encontrada com o ID: " + id);
        }
    }

    public Reserva criar(ReservaInput reservaInput) {
        Reserva reserva = mapeiaReservaInput(reservaInput);
        if(reserva.getDescricaoReserva() == null || reserva.getDescricaoReserva().isEmpty()){
            throw new RuntimeException("A descrição da reserva é obrigatória.");
        }
        if(reserva.getDataReserva() == null){
            throw new RuntimeException("A data da reserva é obrigatória.");
        }
        if(reserva.getDuracao() == null){
            reserva.setDuracao(60*60L); // 1h
        }
        if(reservaInput.getEspacoId() == null){
            throw new RuntimeException("O ID do espaço é obrigatório para a reserva.");
        }
        Espaco espaco = espacoServico.buscar(reservaInput.getEspacoId());
        reserva.setEspaco(espaco);
        
        return reservaRepositorio.save(reserva);
    }

    public void deletar(Long id) throws RuntimeException {
        Reserva reserva = buscar(id);
        reservaRepositorio.delete(reserva);
    }

    public Reserva atualizar(Long id, ReservaInput reservaInput) throws RuntimeException {
        Reserva reservaResult = buscar(id);

        reservaResult.setDescricaoReserva(reservaInput.getDescricaoReserva() != null ? reservaInput.getDescricaoReserva() : reservaResult.getDescricaoReserva());
        reservaResult.setDataCancelamento(reservaInput.getDataCancelamento() != null ? reservaInput.getDataCancelamento() : reservaResult.getDataCancelamento());

        return reservaRepositorio.save(reservaResult);
    }

    public List<Reserva> proximos7dias(){
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime daqui7 = agora.plusDays(7);
        return reservaRepositorio.findAllByDataReservaBetween(agora, daqui7);
    }

    // private

    private Reserva mapeiaReservaInput(ReservaInput reservaInput) {
        Reserva reserva = new Reserva();
        reserva.setDescricaoReserva(reservaInput.getDescricaoReserva());
        reserva.setDataReserva(reservaInput.getDataReserva());
        reserva.setDuracao(reservaInput.getDuracao());
        return reserva;
    }

    
}
