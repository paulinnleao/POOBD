package com.proprietario.service;

import com.proprietario.Proprietario;
import com.proprietario.dto.ProprietarioDTO;
import com.proprietario.repository.ProprietarioRepository;
import com.proprietario.rest.ProprietarioRestImp;
import com.util.exception.ResourceAlreadyExistsException;
import com.util.exception.ResourceNotFoundException;
import com.util.mapper.GlobalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProprietarioServiceImp implements ProprietarioService{
    @Autowired
    private ProprietarioRepository repository;

    @Override
    public ProprietarioDTO findById(Long cpf) {
        Proprietario proprietario = repository.findById(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado proprietario para este CPF!"));
        ProprietarioDTO proprietarioDTO = GlobalMapper.parseObject(proprietario, ProprietarioDTO.class);
        proprietarioDTO.add(
                linkTo(
                        methodOn(ProprietarioRestImp.class).findById(cpf)
                ).withSelfRel()
        );
        return proprietarioDTO;
    }

    @Override
    public List<ProprietarioDTO> findAll() {
        List<Proprietario> listaProprietarios = repository.findAll();
        List<ProprietarioDTO> listaProprietarioDTO = GlobalMapper.parseListObject(listaProprietarios, ProprietarioDTO.class);
        listaProprietarioDTO.forEach(
                proprietario -> proprietario.add(
                        linkTo(
                                methodOn(ProprietarioRestImp.class).findById(proprietario.getCpfProp())
                        ).withSelfRel()
                )
        );
        return listaProprietarioDTO;
    }

    @Override
    public ProprietarioDTO create(ProprietarioDTO proprietarioDTO) {
        if(repository.findById(proprietarioDTO.getCpfProp()).isPresent()){
            throw new ResourceAlreadyExistsException("Já existe proprietário cadastrado com este CPF!");
        }
        Proprietario proprietario = GlobalMapper.parseObject(proprietarioDTO, Proprietario.class);
        ProprietarioDTO proprietarioDTOSaved = GlobalMapper.parseObject(
                repository.save(proprietario),
                ProprietarioDTO.class
        );
        proprietarioDTOSaved.add(
                linkTo(
                        methodOn(ProprietarioRestImp.class).findById(proprietarioDTOSaved.getCpfProp())
                ).withSelfRel()
        );
        return proprietarioDTOSaved;
    }

    @Override
    public ProprietarioDTO update(ProprietarioDTO proprietarioDTO) {
         if(repository.findById(proprietarioDTO.getCpfProp()).isEmpty()){
             throw new ResourceNotFoundException("Não existe pessoa cadastrada com este CPF!");
         }
        Proprietario proprietario = GlobalMapper.parseObject(proprietarioDTO, Proprietario.class);
        ProprietarioDTO proprietarioDTOSaved = GlobalMapper.parseObject(
                repository.save(proprietario),
                ProprietarioDTO.class
        );
        proprietarioDTOSaved.add(
                linkTo(
                        methodOn(ProprietarioRestImp.class).findById(proprietarioDTOSaved.getCpfProp())
                ).withSelfRel()
        );
        return proprietarioDTOSaved;
    }

    @Override
    public ResponseEntity<?> delete(Long cpf) {
        Proprietario proprietario = repository.findById(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado proprietario para este CPF!"));
        repository.delete(proprietario);
        return ResponseEntity.noContent().build();
    }
}
