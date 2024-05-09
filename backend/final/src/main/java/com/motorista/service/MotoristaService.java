package com.motorista.service;

import com.motorista.dto.MotoristaDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MotoristaService {
    MotoristaDTO findById(Long id);
    MotoristaDTO create(MotoristaDTO MotoristaDTO);
    MotoristaDTO update(MotoristaDTO motoristaDTO);
    List<MotoristaDTO> findAll();
    ResponseEntity<?> delete(Long id);
}
