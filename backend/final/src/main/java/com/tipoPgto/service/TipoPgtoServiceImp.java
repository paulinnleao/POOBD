package com.tipoPgto.service;

import com.tipoPgto.TipoPgto;
import com.tipoPgto.dto.TipoPgtoDTO;
import com.tipoPgto.repository.TipoPgtoRepository;
import com.tipoPgto.rest.TipoPgtoRestImp;
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
public class TipoPgtoServiceImp implements TipoPgtoService{
    @Autowired
    private TipoPgtoRepository repository;

    @Override
    public TipoPgtoDTO findById(Integer id) {
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
                                methodOn(TipoPgtoRestImp.class).findById(tipoPgto.getCodPagto())
                        ).withSelfRel()
                )
        );
        return listaTipoPgtoDTO;
    }

    @Override
    public TipoPgtoDTO create(TipoPgtoDTO tipoPgtoDTO) {
        if(repository.findById(tipoPgtoDTO.getCodPagto()).isPresent()){
            throw new ResourceAlreadyExistsException("Já existe pagamento cadastrado para este ID!");
        }
        TipoPgto tipoPgto = GlobalMapper.parseObject(tipoPgtoDTO, TipoPgto.class);
        TipoPgtoDTO tipoPgtoDTOSaved = GlobalMapper.parseObject(
                repository.save(tipoPgto),
                TipoPgtoDTO.class
        );
        tipoPgtoDTOSaved.add(
                linkTo(
                        methodOn(TipoPgtoRestImp.class).findById(tipoPgtoDTOSaved.getCodPagto())
                ).withSelfRel()
        );
        return tipoPgtoDTOSaved;
    }

    @Override
    public TipoPgtoDTO update(TipoPgtoDTO tipoPgtoDTO) {
        if(repository.findById(tipoPgtoDTO.getCodPagto()).isEmpty()){
            throw new ResourceNotFoundException("Não foi encontrado tipo de pagamento para este ID!");
        }
        TipoPgto tipoPgto = GlobalMapper.parseObject(tipoPgtoDTO, TipoPgto.class);
        TipoPgtoDTO tipoPgtoDTOSaved = GlobalMapper.parseObject(
                repository.save(tipoPgto),
                TipoPgtoDTO.class
        );
        tipoPgtoDTOSaved.add(
                linkTo(
                        methodOn(TipoPgtoRestImp.class).findById(tipoPgtoDTOSaved.getCodPagto())
                ).withSelfRel()
        );
        return tipoPgtoDTOSaved;
    }

    @Override
    public ResponseEntity<?> delete(Integer id) {
        TipoPgto tipoPgto = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado tipo de pagamento para este ID!"));
        repository.delete(tipoPgto);
        return ResponseEntity.noContent().build();
    }
}
