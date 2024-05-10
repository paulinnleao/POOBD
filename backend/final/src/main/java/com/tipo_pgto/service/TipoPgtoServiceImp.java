package com.tipo_pgto.service;

import com.tipo_pgto.TipoPgto;
import com.tipo_pgto.dto.TipoPgtoDTO;
import com.tipo_pgto.repository.TipoPgtoRepository;
import com.tipo_pgto.rest.TipoPgtoRestImp;
import com.util.exception.ResourceNotFoundException;
import com.util.mapper.GlobalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class TipoPgtoServiceImp implements TipoPgtoService{
    @Autowired
    private TipoPgtoRepository repository;

    @Override
    public TipoPgtoDTO findById(Long id) {
        TipoPgto tipoPgto = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado tipo de pagamento para este ID!"));
        TipoPgtoDTO tipoPgtoDTO = GlobalMapper.parseObject(tipoPgto, TipoPgtoDTO.class);
        tipoPgtoDTO.add(
                linkTo(
                        methodOn(TipoPgtoRestImp.class).findById(id)
                ).withSelfRel()
        );
        return tipoPgtoDTO;
    }

    @Override
    public List<TipoPgtoDTO> findAll() {
        List<TipoPgto> listaTipoPgtos = repository.findAll();
        List<TipoPgtoDTO> listaTipoPgtoDTO = GlobalMapper.parseListObject(listaTipoPgtos, TipoPgtoDTO.class);
        listaTipoPgtoDTO.forEach(
                tipoPgto -> tipoPgto.add(
                        linkTo(
                                methodOn(TipoPgtoRestImp.class).findById(tipoPgto.getId_tipo_pgto())
                        ).withSelfRel()
                )
        );
        return listaTipoPgtoDTO;
    }

    @Override
    public TipoPgtoDTO create(TipoPgtoDTO tipoPgtoDTO) {
        TipoPgto tipoPgto = GlobalMapper.parseObject(tipoPgtoDTO, TipoPgto.class);
        TipoPgtoDTO tipoPgtoDTOSaved = GlobalMapper.parseObject(
                repository.save(tipoPgto),
                TipoPgtoDTO.class
        );
        tipoPgtoDTOSaved.add(
                linkTo(
                        methodOn(TipoPgtoRestImp.class).findById(tipoPgtoDTOSaved.getId_tipo_pgto())
                ).withSelfRel()
        );
        return tipoPgtoDTOSaved;
    }

    @Override
    public TipoPgtoDTO update(TipoPgtoDTO tipoPgtoDTO) {
        TipoPgto tipoPgto = repository.findById(tipoPgtoDTO.getId_tipo_pgto())
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado tipo de pagamento para este ID!"));
        TipoPgtoDTO tipoPgtoDTOSaved = GlobalMapper.parseObject(
                repository.save(tipoPgto),
                TipoPgtoDTO.class
        );
        tipoPgtoDTOSaved.add(
                linkTo(
                        methodOn(TipoPgtoRestImp.class).findById(tipoPgtoDTOSaved.getId_tipo_pgto())
                ).withSelfRel()
        );
        return tipoPgtoDTOSaved;
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        TipoPgto tipoPgto = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado tipo de pagamento para este ID!"));
        repository.delete(tipoPgto);
        return ResponseEntity.noContent().build();
    }
}
