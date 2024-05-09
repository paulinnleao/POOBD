package com.pessoa.service;

import com.pessoa.dto.PessoaDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PessoaService {

    PessoaDTO findById(Long id);
    PessoaDTO create(PessoaDTO pessoaDTO);
    PessoaDTO update(PessoaDTO pessoaDTO);
    List<PessoaDTO> findAll();
    ResponseEntity<?> delete(Long id);

}
