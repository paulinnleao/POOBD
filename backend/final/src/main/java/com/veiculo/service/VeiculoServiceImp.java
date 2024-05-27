package com.veiculo.service;

import com.util.exception.ResourceAlreadyExistsException;
import com.util.exception.ResourceNotFoundException;
import com.util.mapper.GlobalMapper;
import com.veiculo.Veiculo;
import com.veiculo.dto.VeiculoDTO;
import com.veiculo.repository.VeiculoRepository;
import com.veiculo.rest.VeiculoRestImp;
import com.viagem.dto.ViagemDTO;
import com.viagem.service.ViagemServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class VeiculoServiceImp implements VeiculoService {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Autowired
    private VeiculoRepository repository;

    @Autowired
    private ViagemServiceImp viagemService;

    @Override
    public VeiculoDTO findById(String placa) {
        Veiculo veiculo = repository.findById(placa)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado veículo para esta placa!"));
        VeiculoDTO veiculoDTO = GlobalMapper.parseObject(veiculo, VeiculoDTO.class);
        veiculoDTO.add(
                linkTo(
                        methodOn(VeiculoRestImp.class).findById(placa)
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
                                methodOn(VeiculoRestImp.class).findById(veiculo.getPlaca())
                        ).withSelfRel()
                )
        );
        return listaVeiculoDTO;
    }

    @Override
    public VeiculoDTO create(VeiculoDTO veiculoDTO) {
        if(repository.findById(veiculoDTO.getPlaca()).isPresent()){
            throw new ResourceAlreadyExistsException("Já existe veículo cadastrado para esta placa!");
        }
        Veiculo veiculo = GlobalMapper.parseObject(veiculoDTO, Veiculo.class);
        VeiculoDTO veiculoDTOSaved = GlobalMapper.parseObject(
                repository.save(veiculo),
                VeiculoDTO.class
        );
        veiculoDTOSaved.add(
                linkTo(
                        methodOn(VeiculoRestImp.class).findById(veiculoDTOSaved.getPlaca())
                ).withSelfRel()
        );
        return veiculoDTOSaved;
    }

    @Override
    public VeiculoDTO update(VeiculoDTO veiculoAtualizado) {
        if(repository.findById(veiculoAtualizado.getPlaca()).isEmpty()){
            throw  new ResourceNotFoundException("Não foi encontrado este veículo para atualizar!");
        }
        Veiculo veiculo = GlobalMapper.parseObject(veiculoAtualizado, Veiculo.class);
        VeiculoDTO veiculoDTOSaved = GlobalMapper.parseObject(
                repository.save(veiculo),
                VeiculoDTO.class
        );
        veiculoDTOSaved.add(
                linkTo(
                        methodOn(VeiculoRestImp.class).findById(veiculoDTOSaved.getPlaca())
                ).withSelfRel()
        );
        return veiculoDTOSaved;
    }

    @Override
    public ResponseEntity<?> delete(String placa) {
        Veiculo veiculo = repository.findById(placa)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado veículo para esta placa!"));
        repository.delete(veiculo);
        return ResponseEntity.noContent().build();
    }
    @Override
    public List<VeiculoDTO> findByDate(String data, String horaInicio, String horaFinal){
        LocalDateTime dtHoraInicio = LocalDateTime.parse(data + "T" + horaInicio, formatter);
        LocalDateTime dtHoraFim = LocalDateTime.parse(data + "T" + horaFinal, formatter);
    List<ViagemDTO> viagensBuscadas = viagemService.findByDate(dtHoraInicio, dtHoraFim);
    List<VeiculoDTO> veiculosBuscadosDTO = new ArrayList<>();
    viagensBuscadas.forEach(viagem -> repository.findById(viagem.getPlaca()));
    veiculosBuscadosDTO.forEach(
                veiculo -> veiculo.add(
                        linkTo(
                                methodOn(VeiculoRestImp.class).findById(veiculo.getPlaca())
                        ).withSelfRel()
                )
        );
        return veiculosBuscadosDTO;
    }
}
