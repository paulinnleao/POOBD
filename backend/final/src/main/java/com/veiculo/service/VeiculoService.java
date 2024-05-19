package com.veiculo.service;

import com.veiculo.dto.VeiculoDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VeiculoService {

    VeiculoDTO findById(String placa);
    List<VeiculoDTO> findAll();
    VeiculoDTO create(VeiculoDTO veiculoDTO);
    VeiculoDTO update(VeiculoDTO veiculoAtualizado);
    ResponseEntity<?> delete(String placa);
}
