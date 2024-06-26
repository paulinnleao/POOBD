package com.pessoa.service;

import com.pessoa.repository.PessoaRepository;
import com.util.exception.ResourceAlreadyExistsException;
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
    public PessoaDTO findById(Long cpf) {
        Pessoa pessoa = repository.findById(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado pessoa para este CPF!"));
        PessoaDTO pessoaDTO = GlobalMapper.parseObject(pessoa, PessoaDTO.class);
        pessoaDTO.add(
                linkTo(
                        methodOn(PessoaRestImp.class).findById(cpf)
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
                                methodOn(PessoaRestImp.class).findById(pessoa.getCpfPessoa())
                        ).withSelfRel()
                )
        );
        return listaViagensDTO;
    }

    @Override
    public PessoaDTO create(PessoaDTO pessoaDTO) {
        if(repository.findById(pessoaDTO.getCpfPessoa()).isPresent()){
            throw new ResourceAlreadyExistsException("Já existe pessoa cadastrada com este CPF!");
        }
        Pessoa pessoa = GlobalMapper.parseObject(pessoaDTO, Pessoa.class);
        PessoaDTO pessoaDTOSaved = GlobalMapper.parseObject(
                repository.save(pessoa),
                PessoaDTO.class
        );
        pessoaDTOSaved.add(
                linkTo(
                        methodOn(PessoaRestImp.class).findById(pessoaDTOSaved.getCpfPessoa())
                ).withSelfRel()
        );
        return pessoaDTOSaved;
    }

    @Override
    public PessoaDTO update(PessoaDTO pessoaDTO) {
        if(repository.findById(pessoaDTO.getCpfPessoa()).isEmpty()){
            throw new ResourceNotFoundException("Não existe pessoa cadastrada com este CPF!");
        }
        Pessoa pessoa = GlobalMapper.parseObject(pessoaDTO, Pessoa.class);
        PessoaDTO pessoaDTOSaved = GlobalMapper.parseObject(
                repository.save(pessoa),
                PessoaDTO.class
        );
        pessoaDTOSaved.add(
                linkTo(
                        methodOn(PessoaRestImp.class).findById(pessoaDTOSaved.getCpfPessoa())
                ).withSelfRel()
        );
        return pessoaDTOSaved;
    }

    @Override
    public ResponseEntity<?> delete(Long cpf) {
        Pessoa pessoa = repository.findById(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado pessoa para este CPF!"));
        repository.delete(pessoa);
        return ResponseEntity.noContent().build();
    }
}
