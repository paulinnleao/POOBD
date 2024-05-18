package com.viagem.service;

import com.viagem.dto.ViagemDTO;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface ViagemService {

    ViagemDTO findById(String placa, Long cpfPassag, Long cpfMotorista, String dthoraInicio);
    ViagemDTO create(ViagemDTO viagemDTO);
    ViagemDTO update(ViagemDTO viagemDTO);
    List<ViagemDTO> findAll();
    ResponseEntity<?> delete(String placa, Long cpfPassag, Long cpfMotorista, String dthoraInicio);

}
