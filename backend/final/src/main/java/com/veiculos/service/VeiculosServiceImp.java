package com.veiculos.service;

import com.util.mapper.GlobalMapper;
import com.veiculos.Veiculos;
import com.veiculos.dto.VeiculosDTO;
import com.veiculos.repository.VeiculosRepository;
import com.veiculos.rest.VeiculosRestImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

public class VeiculosServiceImp implements VeiculosService{
    @Autowired
    VeiculosRepository repository;
    @Override
    public VeiculosDTO findById(String placa) {
        Veiculos veiculo = repository.findById(placa)
                .orElseThrow();
        VeiculosDTO veiculosDTO = GlobalMapper.parseObject(veiculo, VeiculosDTO.class);
        veiculosDTO.add(
                linkTo(
                        methodOn(VeiculosRestImp.class).findById(placa)
                ).withSelfRel()
        );
        return veiculosDTO;
    }

    @Override
    public List<VeiculosDTO> findAll() {
        List<Veiculos> listaVeiculos = repository.findAll();
        List<VeiculosDTO> listaVeiculosDTO = GlobalMapper.parseListObject(listaVeiculos, VeiculosDTO.class);
        listaVeiculosDTO.forEach(
                veiculo -> veiculo.add(
                        linkTo(
                                methodOn(VeiculosRestImp.class).findById(veiculo.getPlaca())
                        ).withSelfRel()
                )
        );
        return listaVeiculosDTO;
    }

    @Override
    public VeiculosDTO create(VeiculosDTO veiculosDTO) {
        Veiculos veiculos = GlobalMapper.parseObject(veiculosDTO, Veiculos.class);
        VeiculosDTO veiculosDTOSaved = GlobalMapper.parseObject(
                repository.save(veiculos),
                VeiculosDTO.class
        );
        veiculosDTOSaved.add(
                linkTo(
                        methodOn(VeiculosRestImp.class).findById(veiculosDTOSaved.getPlaca())
                ).withSelfRel()
        );
        return veiculosDTOSaved;
    }

    @Override
    public VeiculosDTO update(VeiculosDTO veiculosDTO) {
        Veiculos veiculos = repository.findById(veiculosDTO.getPlaca())
                .orElseThrow();
        VeiculosDTO veiculosDTOSaved = GlobalMapper.parseObject(
                repository.save(veiculos),
                VeiculosDTO.class
        );
        veiculosDTOSaved.add(
                linkTo(
                        methodOn(VeiculosRestImp.class).findById(veiculosDTOSaved.getPlaca())
                ).withSelfRel()
        );
        return veiculosDTOSaved;
    }

    @Override
    public ResponseEntity<?> delete(String placa) {
        Veiculos veiculo = repository.findById(placa)
                .orElseThrow();
        repository.delete(veiculo);
        return ResponseEntity.noContent().build();
    }
}
