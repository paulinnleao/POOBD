package com.motorista.service;

import com.motorista.Motorista;
import com.motorista.dto.MotoristaDTO;
import com.motorista.repository.MotoristaRepository;
import com.motorista.rest.MotoristaRestImp;
import com.util.exception.ResourceNotFoundException;
import com.util.mapper.GlobalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class MotoristaServiceImp implements MotoristaService {


    @Autowired
    MotoristaRepository repository;

    @Override
    public MotoristaDTO findById(Long id) {
        Motorista motorista = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado motorista para este ID!"));
        MotoristaDTO motoristaDTO = GlobalMapper.parseObject(motorista, MotoristaDTO.class);
        motoristaDTO.add(
                linkTo(
                        methodOn(MotoristaRestImp.class).findById(id)
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
                                methodOn(MotoristaRestImp.class).findById(motorista.getId_motorista())
                        ).withSelfRel()
                )
        );
        return listaViagensDTO;
    }

    @Override
    public MotoristaDTO create(MotoristaDTO motoristaDTO) {
        Motorista motorista = GlobalMapper.parseObject(motoristaDTO, Motorista.class);
        MotoristaDTO motoristaDTOSaved = GlobalMapper.parseObject(
                repository.save(motorista),
                MotoristaDTO.class
        );
        motoristaDTOSaved.add(
                linkTo(
                        methodOn(MotoristaRestImp.class).findById(motoristaDTOSaved.getId_motorista())
                ).withSelfRel()
        );
        return motoristaDTOSaved;
    }

    @Override
    public MotoristaDTO update(MotoristaDTO motoristaDTO) {
        Motorista motorista = repository.findById(motoristaDTO.getId_motorista())
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado motorista para este ID!"));
        MotoristaDTO motoristaDTOSaved = GlobalMapper.parseObject(
                repository.save(motorista),
                MotoristaDTO.class
        );
        motoristaDTOSaved.add(
                linkTo(
                        methodOn(MotoristaRestImp.class).findById(motoristaDTOSaved.getId_motorista())
                ).withSelfRel()
        );
        return motoristaDTOSaved;
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Motorista motorista = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado motorista para este ID!"));
        repository.delete(motorista);
        return ResponseEntity.noContent().build();
    }
}
