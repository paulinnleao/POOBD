package com.passageiro.service;

import com.passageiro.dto.PassageiroDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PassageiroService {

    PassageiroDTO findById(Long cpf);
    PassageiroDTO create(PassageiroDTO passageiroDTO);
    PassageiroDTO update(PassageiroDTO passageiroDTO);
    List<PassageiroDTO> findAll();
    ResponseEntity<?> delete(Long cpf);
}
