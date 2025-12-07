package com.extensao.senac.backend.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.extensao.senac.backend.modelos.Reserva;

@Repository
public interface DashboardRepositorio extends JpaRepository<Reserva, Long> {
    @Query(value = """
        SELECT  
            COUNT(DISTINCT res.id)      AS qtd_reserva,
            COUNT(DISTINCT esp.id)      AS qtd_espaco,
            COUNT(DISTINCT equip.id)    AS qtd_equipamento
        FROM reserva res
        LEFT JOIN espaco esp 
            ON res.espaco_id = esp.id
        LEFT JOIN equipamento equip
            ON equip.espaco_id = esp.id
        WHERE NOW() <= res.data_reserva
          AND IFNULL(esp.disponivel, true) = true
          AND IFNULL(equip.disponivel, true) = true
        """, 
        nativeQuery = true)
    List<Object[]> buscarQuantidades();

}
