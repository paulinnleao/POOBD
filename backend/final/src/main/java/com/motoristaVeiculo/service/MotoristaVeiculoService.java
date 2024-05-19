package com.motoristaVeiculo.service;

import com.motoristaVeiculo.dto.MotoristaVeiculoDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MotoristaVeiculoService {
    MotoristaVeiculoDTO findById(Long cpfMotorista, String placa);
    MotoristaVeiculoDTO create(MotoristaVeiculoDTO MotoristaVeiculoDTO);
    MotoristaVeiculoDTO update(MotoristaVeiculoDTO motoristaVeiculoDTO, String novaPlaca);
    List<MotoristaVeiculoDTO> findAll();
    ResponseEntity<?> delete(Long cpfMotorista, String placa);
}
