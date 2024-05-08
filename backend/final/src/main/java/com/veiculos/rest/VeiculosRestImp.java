package com.veiculos.rest;

import com.veiculos.dto.VeiculosDTO;
import com.veiculos.service.VeiculosService;
import com.veiculos.service.VeiculosServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/veiculo")
public class VeiculosRestImp implements VeiculosService {

    @Autowired
    VeiculosServiceImp service;

    @GetMapping(value = "/{placa}", produces = MediaType.APPLICATION_JSON_VALUE)
    public VeiculosDTO findById(@PathVariable("placa") String placa) {
        return service.findById(placa);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VeiculosDTO> findAll() {
        return service.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public VeiculosDTO create(@RequestBody VeiculosDTO veiculosDTO) {
        return service.create(veiculosDTO);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public VeiculosDTO update(@RequestBody VeiculosDTO veiculosDTO) {
        return null;
    }

    @DeleteMapping("/{placa}")
    public ResponseEntity<?> delete(@PathVariable("placa") String placa) {
        return service.delete(placa);
    }
}
