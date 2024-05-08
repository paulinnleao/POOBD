package com.veiculos.service;

import com.veiculos.dto.VeiculosDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VeiculosService {

    VeiculosDTO findById(String placa);
    List<VeiculosDTO> findAll();
    VeiculosDTO create(VeiculosDTO veiculosDTO);
    VeiculosDTO update(VeiculosDTO veiculosDTO);
    ResponseEntity<?> delete(String placa);
}
