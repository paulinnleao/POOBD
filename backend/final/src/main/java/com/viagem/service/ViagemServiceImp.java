package com.viagem.service;

import com.util.exception.ResourceNotFoundException;
import com.util.mapper.ViagemMapper;
import com.viagem.Viagem;
import com.viagem.dto.ViagemDTO;
import com.viagem.mapper.ViagemMapper;
import com.viagem.rest.ViagemRestImp;
import com.viagem.repository.ViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ViagemServiceImp implements ViagemService{
    @Autowired
    ViagemRepository repository;

    @Override
    public ViagemDTO findById(Long id) {
        Viagem viagem = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado viagem para este ID!"));
        ViagemDTO viagemDTO = ViagemMapper.parseObject(viagem, ViagemDTO.class);
        viagemDTO.add(
                linkTo(
                        methodOn(ViagemRestImp.class).findById(id)
                ).withSelfRel()
        );
        return viagemDTO;
    }

    @Override
    public List<ViagemDTO> findAll() {
        List<Viagem> listaViagens = repository.findAll();
        List<ViagemDTO> listaViagensDTO = ViagemMapper.parseListObject(listaViagens, ViagemDTO.class);
        listaViagensDTO.forEach(
                viagem -> viagem.add(
                        linkTo(
                                methodOn(ViagemRestImp.class).findById(viagem.getId_viagem())
                        ).withSelfRel()
                )
        );
        return listaViagensDTO;
    }

    @Override
    public ViagemDTO create(ViagemDTO viagemDTO) {
        Viagem viagem = ViagemMapper.parseObject(viagemDTO, Viagem.class);
        ViagemDTO viagemDTOSaved = ViagemMapper.parseObject(
                repository.save(viagem),
                ViagemDTO.class
        );
        viagemDTOSaved.add(
                linkTo(
                        methodOn(ViagemRestImp.class).findById(viagemDTOSaved.getId_viagem())
                ).withSelfRel()
        );
        return viagemDTOSaved;
    }

    @Override
    public ViagemDTO update(ViagemDTO viagemDTO) {
        Viagem viagem = repository.findById(viagemDTO.getId_viagem())
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado viagem para este ID!"));
        ViagemDTO viagemDTOSaved = ViagemMapper.parseObject(
                repository.save(viagem),
                ViagemDTO.class
        );
        viagemDTOSaved.add(
                linkTo(
                        methodOn(ViagemRestImp.class).findById(viagemDTOSaved.getId_viagem())
                ).withSelfRel()
        );
        return viagemDTOSaved;
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Viagem viagem = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado viagem para este ID!"));
        repository.delete(viagem);
        return ResponseEntity.noContent().build();
    }
}
