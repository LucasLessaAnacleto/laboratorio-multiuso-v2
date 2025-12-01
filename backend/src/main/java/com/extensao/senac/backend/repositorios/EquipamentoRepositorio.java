package com.extensao.senac.backend.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.extensao.senac.backend.modelos.Equipamento;

@Repository
public interface EquipamentoRepositorio extends JpaRepository<Equipamento, Long> {
    @Query("SELECT DISTINCT e.categoria FROM Equipamento e")
    List<String> findDistinctCategorias();
}
