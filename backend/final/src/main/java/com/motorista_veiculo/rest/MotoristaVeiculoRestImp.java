package com.motorista_veiculo.rest;

import com.motorista_veiculo.dto.MotoristaVeiculoDTO;
import com.motorista_veiculo.service.MotoristaVeiculoServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motoristas-veiculos")
@Tag(name = "Motoristas Veiculos", description = "Endpoints para gerenciar entidade MotoristaVeiculo.")
public class MotoristaVeiculoRestImp {

    @Autowired
    private MotoristaVeiculoServiceImp service;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Buscar motoristaVeiculo pelo ID", description = "Procura um motoristaVeiculo pelo id. Caso não encontre, retorna um NOT_FOUND",
            tags = {"Motoristas Veiculos"},
            responses = {
                    @ApiResponse(
                            description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = MotoristaVeiculoDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public MotoristaVeiculoDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Busca todos os motoristaVeiculos", description = "Busca todas os motoristaVeiculos cadastrados no Banco de Dados.",
            tags = {"Motoristas Veiculos"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                            @Content (
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(
                                            schema = @Schema(
                                                    implementation = MotoristaVeiculoDTO.class
                                            )
                                    )
                            )}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public List<MotoristaVeiculoDTO> findAll() {
        return service.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Persiste um novo motoristaVeiculo no Banco de Dados", description = "Persiste um novo motoristaVeiculo no Banco de Dados, caso ele não encontre outro com o mesmo ID.",
            tags = {"Motoristas Veiculos"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = MotoristaVeiculoDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public MotoristaVeiculoDTO create(@RequestBody MotoristaVeiculoDTO motoristaVeiculoDTO) {
        return service.create(motoristaVeiculoDTO);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualiza os dados de um motoristaVeiculo", description = "Busca o motoristaVeiculo, atualiza, persiste e retorna o motoristaVeiculo atualizado.",
            tags = {"Motoristas Veiculos"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = MotoristaVeiculoDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public MotoristaVeiculoDTO update(@RequestBody MotoristaVeiculoDTO motoristaVeiculoDTO) {
        return service.update(motoristaVeiculoDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Apaga um motoristaVeiculo através do ID", description = "Busca um motoristaVeiculo pelo ID fornecido e se encontrar, apaga",
            tags = {"Motoristas Veiculos"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }
}
