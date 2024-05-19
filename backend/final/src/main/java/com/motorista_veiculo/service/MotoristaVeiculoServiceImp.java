package com.motorista_veiculo.service;

import com.motorista_veiculo.MotoristaVeiculo;
import com.motorista_veiculo.dto.MotoristaVeiculoDTO;
import com.motorista_veiculo.mapper.MotoristaVeiculoMapper;
import com.motorista_veiculo.repository.MotoristaVeiculoRepository;
import com.motorista_veiculo.rest.MotoristaVeiculoRestImp;
import com.util.exception.ResourceNotFoundException;
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
    public MotoristaVeiculoDTO findById(Long cpfMotorista, String placa) {
        MotoristaVeiculo motoristaVeiculo = repository.findById( new MotoristaVeiculo.MotoristaVeiculoId( cpfMotorista, placa ))
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado motorista e veiculo para este ID!"));
        MotoristaVeiculoDTO motoristaVeiculoDTO = MotoristaVeiculoMapper.parseObject(motoristaVeiculo, MotoristaVeiculoDTO.class);
        motoristaVeiculoDTO.add(
                linkTo(
                        methodOn(MotoristaVeiculoRestImp.class).findById(
                                motoristaVeiculo.getCpfMotorista(),
                                motoristaVeiculo.getPlaca()
                        )
                ).withSelfRel()
        );
        return motoristaVeiculoDTO;
    }

    @Override
    public List<MotoristaVeiculoDTO> findAll() {
        List<MotoristaVeiculo> listaViagens = repository.findAll();
        List<MotoristaVeiculoDTO> listaViagensDTO = MotoristaVeiculoMapper.parseListObject(listaViagens, MotoristaVeiculoDTO.class);
        listaViagensDTO.forEach(
                motoristaVeiculo -> motoristaVeiculo.add(
                        linkTo(
                                methodOn(MotoristaVeiculoRestImp.class).findById(
                                                motoristaVeiculo.getCpfMotorista(),
                                                motoristaVeiculo.getPlaca()
                                        )
                        ).withSelfRel()
                )
        );
        return listaViagensDTO;
    }

    @Override
    public MotoristaVeiculoDTO create(MotoristaVeiculoDTO motoristaVeiculoDTO) {
        MotoristaVeiculo motoristaVeiculo = MotoristaVeiculoMapper.parseObject(motoristaVeiculoDTO, MotoristaVeiculo.class);
        MotoristaVeiculoDTO motoristaVeiculoDTOSaved = MotoristaVeiculoMapper.parseObject(
                repository.save(motoristaVeiculo),
                MotoristaVeiculoDTO.class
        );
        motoristaVeiculoDTOSaved.add(
                linkTo(
                        methodOn(MotoristaVeiculoRestImp.class).findById(
                                motoristaVeiculoDTOSaved.getCpfMotorista(),
                                motoristaVeiculoDTOSaved.getPlaca()
                        )
                ).withSelfRel()
        );
        return motoristaVeiculoDTOSaved;
    }

    @Override
    public MotoristaVeiculoDTO update(MotoristaVeiculoDTO motoristaVeiculoDTO, String novaPlaca) {
        MotoristaVeiculo motoristaVeiculo = repository.findById(
                new MotoristaVeiculo.MotoristaVeiculoId(
                        motoristaVeiculoDTO.getCpfMotorista(),
                        motoristaVeiculoDTO.getPlaca()
                ))
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado motorista e veiculo para este ID!"));
        MotoristaVeiculo novoMotoristaVeiculo = motoristaVeiculo;
        novoMotoristaVeiculo.setPlaca(novaPlaca);
        MotoristaVeiculoDTO motoristaVeiculoDTOSaved = MotoristaVeiculoMapper.parseObject(
                repository.save(novoMotoristaVeiculo),
                MotoristaVeiculoDTO.class
        );
        motoristaVeiculoDTOSaved.add(
                linkTo(
                        methodOn(MotoristaVeiculoRestImp.class).findById(
                                motoristaVeiculoDTOSaved.getCpfMotorista(),
                                motoristaVeiculoDTOSaved.getPlaca()
                        )
                ).withSelfRel()
        );
        return motoristaVeiculoDTOSaved;
    }

    @Override
    public ResponseEntity<?> delete(Long cpfMotorista, String placa) {
        MotoristaVeiculo motoristaVeiculo = repository.findById(
                        new MotoristaVeiculo.MotoristaVeiculoId(
                                cpfMotorista,
                                placa
                        ))
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado motorista e veiculo para este ID!"));
        repository.delete(motoristaVeiculo);
        return ResponseEntity.noContent().build();
    }
}
