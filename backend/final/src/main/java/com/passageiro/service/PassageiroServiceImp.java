package com.passageiro.service;

import com.passageiro.repository.PassageiroRepository;
import com.passageiro.rest.PassageiroRestImp;
import com.util.exception.ResourceAlreadyExistsException;
import com.util.exception.ResourceNotFoundException;
import com.util.mapper.GlobalMapper;
import com.passageiro.Passageiro;
import com.passageiro.dto.PassageiroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PassageiroServiceImp implements PassageiroService {

    @Autowired
    PassageiroRepository repository;

    @Override
    public PassageiroDTO findById(Long cpf) {
        Passageiro passageiro = repository.findById(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado passageiro para este CPF!"));
        PassageiroDTO passageiroDTO = GlobalMapper.parseObject(passageiro, PassageiroDTO.class);
        passageiroDTO.add(
                linkTo(
                        methodOn(PassageiroRestImp.class).findById(cpf)
                ).withSelfRel()
        );
        return passageiroDTO;
    }

    @Override
    public List<PassageiroDTO> findAll() {
        List<Passageiro> listaViagens = repository.findAll();
        List<PassageiroDTO> listaViagensDTO = GlobalMapper.parseListObject(listaViagens, PassageiroDTO.class);
        listaViagensDTO.forEach(
                passageiro -> passageiro.add(
                        linkTo(
                                methodOn(PassageiroRestImp.class).findById(passageiro.getCpfPassg())
                        ).withSelfRel()
                )
        );
        return listaViagensDTO;
    }

    @Override
    public PassageiroDTO create(PassageiroDTO passageiroDTO) {
        if(repository.findById(passageiroDTO.getCpfPassg()).isPresent()){
            throw new ResourceAlreadyExistsException("Já existe passageiro cadastrado com este CPF!");
        }
        Passageiro passageiro = GlobalMapper.parseObject(passageiroDTO, Passageiro.class);
        PassageiroDTO passageiroDTOSaved = GlobalMapper.parseObject(
                repository.save(passageiro),
                PassageiroDTO.class
        );
        passageiroDTOSaved.add(
                linkTo(
                        methodOn(PassageiroRestImp.class).findById(passageiroDTOSaved.getCpfPassg())
                ).withSelfRel()
        );
        return passageiroDTOSaved;
    }

    @Override
    public PassageiroDTO update(PassageiroDTO passageiroDTO) {
        if(repository.findById(passageiroDTO.getCpfPassg()).isEmpty()){
            throw new ResourceNotFoundException("Não existe passageiro cadastrado com este CPF!");
        }
        Passageiro passageiro = GlobalMapper.parseObject(passageiroDTO, Passageiro.class);
        PassageiroDTO passageiroDTOSaved = GlobalMapper.parseObject(
                repository.save(passageiro),
                PassageiroDTO.class
        );
        passageiroDTOSaved.add(
                linkTo(
                        methodOn(PassageiroRestImp.class).findById(passageiroDTOSaved.getCpfPassg())
                ).withSelfRel()
        );
        return passageiroDTOSaved;
    }

    @Override
    public ResponseEntity<?> delete(Long cpf) {
        Passageiro passageiro = repository.findById(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado passageiro para este CPF!"));
        repository.delete(passageiro);
        return ResponseEntity.noContent().build();
    }
}
