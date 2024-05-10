package com.motorista_veiculo.service;

import com.motorista_veiculo.MotoristaVeiculo;
import com.motorista_veiculo.dto.MotoristaVeiculoDTO;
import com.motorista_veiculo.repository.MotoristaVeiculoRepository;
import com.motorista_veiculo.rest.MotoristaVeiculoRestImp;
import com.util.exception.ResourceNotFoundException;
import com.util.mapper.GlobalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class MotoristaVeiculoServiceImp implements MotoristaVeiculoService{

    @Autowired
    MotoristaVeiculoRepository repository;

    @Override
    public MotoristaVeiculoDTO findById(Long id) {
        MotoristaVeiculo motoristaVeiculo = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado motorista e veiculo para este ID!"));
        MotoristaVeiculoDTO motoristaVeiculoDTO = GlobalMapper.parseObject(motoristaVeiculo, MotoristaVeiculoDTO.class);
        motoristaVeiculoDTO.add(
                linkTo(
                        methodOn(MotoristaVeiculoRestImp.class).findById(id)
                ).withSelfRel()
        );
        return motoristaVeiculoDTO;
    }

    @Override
    public List<MotoristaVeiculoDTO> findAll() {
        List<MotoristaVeiculo> listaViagens = repository.findAll();
        List<MotoristaVeiculoDTO> listaViagensDTO = GlobalMapper.parseListObject(listaViagens, MotoristaVeiculoDTO.class);
        listaViagensDTO.forEach(
                motoristaVeiculo -> motoristaVeiculo.add(
                        linkTo(
                                methodOn(MotoristaVeiculoRestImp.class).findById(motoristaVeiculo.getId_motorista_veiculo())
                        ).withSelfRel()
                )
        );
        return listaViagensDTO;
    }

    @Override
    public MotoristaVeiculoDTO create(MotoristaVeiculoDTO motoristaVeiculoDTO) {
        MotoristaVeiculo motoristaVeiculo = GlobalMapper.parseObject(motoristaVeiculoDTO, MotoristaVeiculo.class);
        MotoristaVeiculoDTO motoristaVeiculoDTOSaved = GlobalMapper.parseObject(
                repository.save(motoristaVeiculo),
                MotoristaVeiculoDTO.class
        );
        motoristaVeiculoDTOSaved.add(
                linkTo(
                        methodOn(MotoristaVeiculoRestImp.class).findById(motoristaVeiculoDTOSaved.getId_motorista_veiculo())
                ).withSelfRel()
        );
        return motoristaVeiculoDTOSaved;
    }

    @Override
    public MotoristaVeiculoDTO update(MotoristaVeiculoDTO motoristaVeiculoDTO) {
        MotoristaVeiculo motoristaVeiculo = repository.findById(motoristaVeiculoDTO.getId_motorista_veiculo())
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado motorista e veiculo para este ID!"));
        MotoristaVeiculoDTO motoristaVeiculoDTOSaved = GlobalMapper.parseObject(
                repository.save(motoristaVeiculo),
                MotoristaVeiculoDTO.class
        );
        motoristaVeiculoDTOSaved.add(
                linkTo(
                        methodOn(MotoristaVeiculoRestImp.class).findById(motoristaVeiculoDTOSaved.getId_motorista_veiculo())
                ).withSelfRel()
        );
        return motoristaVeiculoDTOSaved;
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        MotoristaVeiculo motoristaVeiculo = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado motorista e veiculo para este ID!"));
        repository.delete(motoristaVeiculo);
        return ResponseEntity.noContent().build();
    }
}
