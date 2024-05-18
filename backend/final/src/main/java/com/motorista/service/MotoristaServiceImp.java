package com.motorista.service;

import com.motorista.Motorista;
import com.motorista.dto.MotoristaDTO;
import com.motorista.repository.MotoristaRepository;
import com.motorista.rest.MotoristaRestImp;
import com.util.exception.ExceptionResponse;
import com.util.exception.ResourceAlreadyExistsException;
import com.util.exception.ResourceNotFoundException;
import com.util.mapper.GlobalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class MotoristaServiceImp implements MotoristaService {


    @Autowired
    MotoristaRepository repository;

    @Override
    public MotoristaDTO findById(Long cpf) {
        Motorista motorista = repository.findById(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado motorista para este CPF!"));
        MotoristaDTO motoristaDTO = GlobalMapper.parseObject(motorista, MotoristaDTO.class);
        motoristaDTO.add(
                linkTo(
                        methodOn(MotoristaRestImp.class).findById(cpf)
                ).withSelfRel()
        );
        return motoristaDTO;
    }

    @Override
    public List<MotoristaDTO> findAll() {
        List<Motorista> listaViagens = repository.findAll();
        List<MotoristaDTO> listaViagensDTO = GlobalMapper.parseListObject(listaViagens, MotoristaDTO.class);
        listaViagensDTO.forEach(
                motorista -> motorista.add(
                        linkTo(
                                methodOn(MotoristaRestImp.class).findById(motorista.getCpfMotorista())
                        ).withSelfRel()
                )
        );
        return listaViagensDTO;
    }

    @Override
    public MotoristaDTO create(MotoristaDTO motoristaDTO) {
        Motorista motorista = GlobalMapper.parseObject(motoristaDTO, Motorista.class);
        if(repository.findById(motorista.getCpfMotorista()).isPresent()) {
           throw new ResourceAlreadyExistsException("Já existe motorista cadastrado com este CPF! ");
        }
        MotoristaDTO motoristaDTOSaved = GlobalMapper.parseObject(
                repository.save(motorista),
                MotoristaDTO.class
        );
        motoristaDTOSaved.add(
                linkTo(
                        methodOn(MotoristaRestImp.class).findById(motoristaDTOSaved.getCpfMotorista())
                ).withSelfRel()
        );
        return motoristaDTOSaved;
    }

    @Override
    public MotoristaDTO update(MotoristaDTO motoristaDTO) {
        Motorista motorista = repository.findById(motoristaDTO.getCpfMotorista())
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado motorista para este CPF!"));
        MotoristaDTO motoristaDTOSaved = GlobalMapper.parseObject(
                repository.save(motorista),
                MotoristaDTO.class
        );
        motoristaDTOSaved.add(
                linkTo(
                        methodOn(MotoristaRestImp.class).findById(motoristaDTOSaved.getCpfMotorista())
                ).withSelfRel()
        );
        return motoristaDTOSaved;
    }

    @Override
    public ResponseEntity<?> delete(Long cpf) {
        Motorista motorista = repository.findById(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado motorista para este CPF!"));
        repository.delete(motorista);
        return ResponseEntity.noContent().build();
    }
}
