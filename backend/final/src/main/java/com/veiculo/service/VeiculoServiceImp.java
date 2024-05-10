package com.veiculo.service;

import com.util.exception.ResourceNotFoundException;
import com.util.mapper.GlobalMapper;
import com.veiculo.Veiculo;
import com.veiculo.dto.VeiculoDTO;
import com.veiculo.repository.VeiculoRepository;
import com.veiculo.rest.VeiculoRestImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@Service
public class VeiculoServiceImp implements VeiculoService {
    @Autowired
    private VeiculoRepository repository;

    @Override
    public VeiculoDTO findById(Long id_veiculo) {
        Veiculo veiculo = repository.findById(id_veiculo)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado veículo para esta placa!"));
        VeiculoDTO veiculoDTO = GlobalMapper.parseObject(veiculo, VeiculoDTO.class);
        veiculoDTO.add(
                linkTo(
                        methodOn(VeiculoRestImp.class).findById(id_veiculo)
                ).withSelfRel()
        );
        return veiculoDTO;
    }

    @Override
    public List<VeiculoDTO> findAll() {
        List<Veiculo> listaVeiculos = repository.findAll();
        List<VeiculoDTO> listaVeiculoDTO = GlobalMapper.parseListObject(listaVeiculos, VeiculoDTO.class);
        listaVeiculoDTO.forEach(
                veiculo -> veiculo.add(
                        linkTo(
                                methodOn(VeiculoRestImp.class).findById(veiculo.getId_veiculo())
                        ).withSelfRel()
                )
        );
        return listaVeiculoDTO;
    }

    @Override
    public VeiculoDTO create(VeiculoDTO veiculoDTO) {
        Veiculo veiculo = GlobalMapper.parseObject(veiculoDTO, Veiculo.class);
        VeiculoDTO veiculoDTOSaved = GlobalMapper.parseObject(
                repository.save(veiculo),
                VeiculoDTO.class
        );
        veiculoDTOSaved.add(
                linkTo(
                        methodOn(VeiculoRestImp.class).findById(veiculoDTOSaved.getId_veiculo())
                ).withSelfRel()
        );
        return veiculoDTOSaved;
    }

    @Override
    public VeiculoDTO update(VeiculoDTO veiculoDTO) {
        Veiculo veiculo = repository.findById(veiculoDTO.getId_veiculo())
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado este veículo para atualizar!"));
        VeiculoDTO veiculoDTOSaved = GlobalMapper.parseObject(
                repository.save(veiculo),
                VeiculoDTO.class
        );
        veiculoDTOSaved.add(
                linkTo(
                        methodOn(VeiculoRestImp.class).findById(veiculoDTOSaved.getId_veiculo())
                ).withSelfRel()
        );
        return veiculoDTOSaved;
    }

    @Override
    public ResponseEntity<?> delete(Long id_veiculo) {
        Veiculo veiculo = repository.findById(id_veiculo)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado veículo para esta placa!"));
        repository.delete(veiculo);
        return ResponseEntity.noContent().build();
    }
}
