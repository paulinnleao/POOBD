package com.proprietario.service;

import com.proprietario.dto.ProprietarioDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProprietarioService {

    ProprietarioDTO findById(Long cpf);
    ProprietarioDTO create(ProprietarioDTO proprietarioDTO);
    ProprietarioDTO update(ProprietarioDTO proprietarioDTO);
    List<ProprietarioDTO> findAll();
    ResponseEntity<?> delete(Long cpf);
}
