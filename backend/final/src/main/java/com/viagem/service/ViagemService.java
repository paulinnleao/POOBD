package com.viagem.service;

import com.util.atividades.ViagensMediaMensalSexo;
import com.viagem.dto.ViagemDTO;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface ViagemService {

    ViagemDTO findById( Long cpfPassag, Long cpfMotorista, String placa, String dthoraInicio);
    ViagemDTO create(ViagemDTO viagemDTO);
    ViagemDTO update(ViagemDTO viagemDTO);
    List<ViagemDTO> findAll();
    ResponseEntity<?> delete(String placa, Long cpfPassag, Long cpfMotorista, String dthoraInicio);
    // Fase 02 - atividade 01
    List<ViagemDTO> findByDate(LocalDateTime dtHoraInicio, LocalDateTime dtHoraFim);

    // Fase 02 - atividade 02
    List<ViagemDTO> faturamentoPorMes(Integer quantidade, Integer mes);

    // Fase 02 - atividade 04
    ResponseEntity<List<ViagensMediaMensalSexo>> buscarViagensMediaMensalSexo();
}
