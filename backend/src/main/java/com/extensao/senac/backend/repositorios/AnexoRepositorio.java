package com.extensao.senac.backend.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.extensao.senac.backend.modelos.Anexo;

@Repository
public interface AnexoRepositorio extends JpaRepository<Anexo, String> {}
