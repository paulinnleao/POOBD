package com.pessoa.service;

import com.pessoa.repository.PessoaRepository;
import com.util.exception.ResourceNotFoundException;
import com.util.mapper.GlobalMapper;
import com.pessoa.Pessoa;
import com.pessoa.dto.PessoaDTO;
import com.pessoa.rest.PessoaRestImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PessoaServiceImp implements PessoaService {
    @Autowired
    PessoaRepository repository;

    @Override
    public PessoaDTO findById(Long id) {
        Pessoa pessoa = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado pessoa para este ID!"));
        PessoaDTO pessoaDTO = GlobalMapper.parseObject(pessoa, PessoaDTO.class);
        pessoaDTO.add(
                linkTo(
                        methodOn(PessoaRestImp.class).findById(id)
                ).withSelfRel()
        );
        return pessoaDTO;
    }

    @Override
    public List<PessoaDTO> findAll() {
        List<Pessoa> listaViagens = repository.findAll();
        List<PessoaDTO> listaViagensDTO = GlobalMapper.parseListObject(listaViagens, PessoaDTO.class);
        listaViagensDTO.forEach(
                pessoa -> pessoa.add(
                        linkTo(
                                methodOn(PessoaRestImp.class).findById(pessoa.getCpf_pessoa())
                        ).withSelfRel()
                )
        );
        return listaViagensDTO;
    }

    @Override
    public PessoaDTO create(PessoaDTO pessoaDTO) {
        Pessoa pessoa = GlobalMapper.parseObject(pessoaDTO, Pessoa.class);
        PessoaDTO pessoaDTOSaved = GlobalMapper.parseObject(
                repository.save(pessoa),
                PessoaDTO.class
        );
        pessoaDTOSaved.add(
                linkTo(
                        methodOn(PessoaRestImp.class).findById(pessoaDTOSaved.getCpf_pessoa())
                ).withSelfRel()
        );
        return pessoaDTOSaved;
    }

    @Override
    public PessoaDTO update(PessoaDTO pessoaDTO) {
        Pessoa pessoa = repository.findById(pessoaDTO.getCpf_pessoa())
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado pessoa para este ID!"));
        PessoaDTO pessoaDTOSaved = GlobalMapper.parseObject(
                repository.save(pessoa),
                PessoaDTO.class
        );
        pessoaDTOSaved.add(
                linkTo(
                        methodOn(PessoaRestImp.class).findById(pessoaDTOSaved.getCpf_pessoa())
                ).withSelfRel()
        );
        return pessoaDTOSaved;
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Pessoa pessoa = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado pessoa para este ID!"));
        repository.delete(pessoa);
        return ResponseEntity.noContent().build();
    }
}
