package com.tipo_pgto.service;

import com.tipo_pgto.dto.TipoPgtoDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TipoPgtoService {

    TipoPgtoDTO findById(Integer id);
    TipoPgtoDTO create(TipoPgtoDTO tipoPgtoDTO);
    TipoPgtoDTO update(TipoPgtoDTO tipoPgtoDTO);
    List<TipoPgtoDTO> findAll();
    ResponseEntity<?> delete(Integer id);
}
