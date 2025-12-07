package com.extensao.senac.backend.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extensao.senac.backend.dto.DashboardOutput;
import com.extensao.senac.backend.repositorios.DashboardRepositorio;

@Service
public class DashboardServico {
    @Autowired
    private DashboardRepositorio dashboardRepositorio;

    public DashboardOutput dashboard() {
        List<Object[]> lista = dashboardRepositorio.buscarQuantidades();
        try {
            Object[] result = lista.get(0);
    
            Long qtdReserva     = result[0] != null ? ((Number) result[0]).longValue() : 0L;
            Long qtdEspaco      = result[1] != null ? ((Number) result[1]).longValue() : 0L;
            Long qtdEquipamento = result[2] != null ? ((Number) result[2]).longValue() : 0L;
    
            return new DashboardOutput(qtdReserva, qtdEspaco, qtdEquipamento);
        } catch (Exception e) {
            return new DashboardOutput(0L, 0L, 0L);
        }
    }
}   
