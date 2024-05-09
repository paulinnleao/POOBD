package com.viagem.service;

import com.viagem.dto.ViagemDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ViagemService {

    ViagemDTO findById(Long id);
    ViagemDTO create(ViagemDTO viagemDTO);
    ViagemDTO update(ViagemDTO viagemDTO);
    List<ViagemDTO> findAll();
    ResponseEntity<?> delete(Long id);

}
