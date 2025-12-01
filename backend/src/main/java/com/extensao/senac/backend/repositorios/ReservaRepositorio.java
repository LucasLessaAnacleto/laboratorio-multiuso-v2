package com.extensao.senac.backend.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.extensao.senac.backend.modelos.Reserva;

@Repository
public interface ReservaRepositorio extends JpaRepository<Reserva, Long> {
    List<Reserva> findAllByEspacoId(String espacoId);
}
