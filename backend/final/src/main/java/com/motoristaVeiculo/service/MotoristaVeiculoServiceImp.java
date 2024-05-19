package com.motoristaVeiculo.service;

import com.motoristaVeiculo.MotoristaVeiculo;
import com.motoristaVeiculo.dto.MotoristaVeiculoDTO;
import com.motoristaVeiculo.mapper.MotoristaVeiculoMapper;
import com.motoristaVeiculo.repository.MotoristaVeiculoRepository;
import com.motoristaVeiculo.rest.MotoristaVeiculoRestImp;
import com.util.exception.ResourceAlreadyExistsException;
import com.util.exception.ResourceNotFoundException;
import com.viagem.dto.ViagemDTO;
import com.viagem.mapper.ViagemMapper;
import com.viagem.service.ViagemServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class MotoristaVeiculoServiceImp implements MotoristaVeiculoService{

    @Autowired
    MotoristaVeiculoRepository repository;

    @Autowired
    ViagemServiceImp viagemServiceImp;

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
    @Transactional
    public MotoristaVeiculoDTO create(MotoristaVeiculoDTO motoristaVeiculoDTO) {
        MotoristaVeiculo motoristaVeiculo = MotoristaVeiculoMapper.parseObject(motoristaVeiculoDTO, MotoristaVeiculo.class);
        if(repository.findById(motoristaVeiculo.getMotoristaVeiculoId()).isPresent()){
            throw new ResourceAlreadyExistsException("Já existe um Motorista com este Veículo cadastrado!");
        }
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
    @Transactional
    public MotoristaVeiculoDTO update(MotoristaVeiculoDTO motoristaVeiculoDTO, String novaPlaca) {
        if(repository.findById(new MotoristaVeiculo.MotoristaVeiculoId(
                        motoristaVeiculoDTO.getCpfMotorista(),
                        motoristaVeiculoDTO.getPlaca()
                )).isEmpty()){
            throw new ResourceNotFoundException("Não foi encontrado motorista e veiculo para este ID!");
        }else if(repository.findById(new MotoristaVeiculo.MotoristaVeiculoId(
                motoristaVeiculoDTO.getCpfMotorista(),
                novaPlaca
        )).isPresent()){
            throw new ResourceAlreadyExistsException("Já existe um Motorista com este Veículo cadastrado!");
        }
        MotoristaVeiculo novoMotoristaVeiculo = MotoristaVeiculoMapper.parseObject(motoristaVeiculoDTO, MotoristaVeiculo.class);
        novoMotoristaVeiculo.getListaViagens().size();
        if(Objects.nonNull(novoMotoristaVeiculo.getListaViagens())){
            novoMotoristaVeiculo.getListaViagens().forEach(
                    viagem -> {
                        viagem.setPlaca(novaPlaca);
                        viagemServiceImp.update(ViagemMapper.parseObject(viagem, ViagemDTO.class));
                    }
            );
        }
//        repository.deleteById(new MotoristaVeiculo.MotoristaVeiculoId(
//                motoristaVeiculoDTO.getCpfMotorista(),
//                motoristaVeiculoDTO.getPlaca()
//        ));
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
        MotoristaVeiculo motoristaVeiculo = repository.findById( new MotoristaVeiculo.MotoristaVeiculoId( cpfMotorista, placa ))
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado motorista e veiculo para este ID!"));
        repository.delete(motoristaVeiculo);
        return ResponseEntity.noContent().build();
    }
}
