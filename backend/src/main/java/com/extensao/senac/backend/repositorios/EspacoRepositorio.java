package com.extensao.senac.backend.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.extensao.senac.backend.modelos.Espaco;

@Repository
public interface EspacoRepositorio extends JpaRepository<Espaco, String> {
    boolean existsByNome(String nome);

}
