package com.veiculo.service;

import com.veiculo.dto.VeiculoDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VeiculoService {

    VeiculoDTO findById(Long id_veiculo);
    List<VeiculoDTO> findAll();
    VeiculoDTO create(VeiculoDTO veiculoDTO);
    VeiculoDTO update(VeiculoDTO veiculoDTO);
    ResponseEntity<?> delete(Long id_veiculo);
}
