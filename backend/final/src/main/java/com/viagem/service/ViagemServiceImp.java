package com.viagem.service;

import com.util.exception.ResourceNotFoundException;
import com.viagem.Viagem;
import com.viagem.dto.ViagemDTO;
import com.viagem.mapper.ViagemMapper;
import com.viagem.rest.ViagemRestImp;
import com.viagem.repository.ViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ViagemServiceImp implements ViagemService{
    @Autowired
    ViagemRepository repository;

    @Override
    public ViagemDTO findById(String placa, Long cpfPassag, Long cpfMotorista, LocalDateTime dthoraInicio) {

        Viagem viagem = repository.findById(new Viagem.ViagemId(placa, cpfPassag, cpfMotorista, dthoraInicio))
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado viagem para este ID!"));
        ViagemDTO viagemDTO = ViagemMapper.parseObject(viagem, ViagemDTO.class);
        viagemDTO.add(
                linkTo(
                        methodOn(ViagemRestImp.class).findById(placa, cpfPassag, cpfMotorista, dthoraInicio)
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
                                methodOn(ViagemRestImp.class).findById(
                                        viagem.getPlaca(),
                                        viagem.getCpfPassag(),
                                        viagem.getCpfMotorista(),
                                        viagem.getDtHoraInicio()
                                )
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
                        methodOn(ViagemRestImp.class).findById(
                                viagemDTOSaved.getPlaca(),
                                viagemDTOSaved.getCpfPassag(),
                                viagemDTOSaved.getCpfMotorista(),
                                viagemDTOSaved.getDtHoraInicio()
                        )
                ).withSelfRel()
        );
        return viagemDTOSaved;
    }

    @Override
    public ViagemDTO update(ViagemDTO viagemDTO) {
        Viagem viagem = repository.findById(
                new Viagem.ViagemId(
                        viagemDTO.getPlaca(),
                        viagemDTO.getCpfPassag(),
                        viagemDTO.getCpfMotorista(),
                        viagemDTO.getDtHoraInicio()
                ))
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado viagem para este ID!"));
        ViagemDTO viagemDTOSaved = ViagemMapper.parseObject(
                repository.save(viagem),
                ViagemDTO.class
        );
        viagemDTOSaved.add(
                linkTo(
                        methodOn(ViagemRestImp.class).findById(
                                viagemDTOSaved.getPlaca(),
                                viagemDTOSaved.getCpfPassag(),
                                viagemDTOSaved.getCpfMotorista(),
                                viagemDTOSaved.getDtHoraInicio()
                        )
                ).withSelfRel()
        );
        return viagemDTOSaved;
    }

    @Override
    public ResponseEntity<?> delete(String placa, Long cpfPassag, Long cpfMotorista, LocalDateTime dthoraInicio) {
        Viagem viagem = repository.findById(new Viagem.ViagemId(placa, cpfPassag, cpfMotorista, dthoraInicio))
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado viagem para este ID!"));
        repository.delete(viagem);
        return ResponseEntity.noContent().build();
    }
}
