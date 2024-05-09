package com.motorista_veiculo.service;

import com.motorista_veiculo.dto.MotoristaVeiculoDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MotoristaVeiculoService {
    MotoristaVeiculoDTO findById(Long id);
    MotoristaVeiculoDTO create(MotoristaVeiculoDTO MotoristaVeiculoDTO);
    MotoristaVeiculoDTO update(MotoristaVeiculoDTO motoristaVeiculoDTO);
    List<MotoristaVeiculoDTO> findAll();
    ResponseEntity<?> delete(Long id);
}
