package com.veiculos.rest;

import com.veiculos.dto.VeiculosDTO;
import com.veiculos.service.VeiculosService;
import com.veiculos.service.VeiculosServiceImp;
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
@RequestMapping(value = "/veiculo")
@Tag(name = "Veiculo", description = "Endpoints para gerenciar entidade Veículos.")
public class VeiculosRestImp implements VeiculosService {

    @Autowired
    private VeiculosServiceImp service;

    @GetMapping(value = "/{placa}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Buscar veiculo pela placa", description = "Procura um veículo pela placa. Caso não encontre, retorna um NOT_FOUND",
                tags = {"Veiculo"},
                responses = {
                    @ApiResponse(
                            description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = VeiculosDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                })
    public VeiculosDTO findById(@PathVariable("placa") String placa) {
        return service.findById(placa);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Busca todos os veículos", description = "Busca todos os veículos cadastrados no Banco de Dados.",
                tags = {"Veiculo"},
                responses = {
                        @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                                @Content (
                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                        array = @ArraySchema(
                                                schema = @Schema(
                                                        implementation = VeiculosDTO.class
                                                )
                                        )
                                )}),
                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                })
    public List<VeiculosDTO> findAll() {
        return service.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Persiste um novo veículo no Banco de Dados", description = "Persiste um novo veículo no Banco de Dados, caso ele não encontre outro com a mesma placa.",
                tags = {"Veiculo"},
                responses = {
                        @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = VeiculosDTO.class))),
                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                })
    public VeiculosDTO create(@RequestBody VeiculosDTO veiculosDTO) {
        return service.create(veiculosDTO);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualiza os dados de um veículo", description = "Busca o veículo, atualiza, persiste e retorna o veículo atualizado.",
                tags = {"Veiculo"},
                responses = {
                        @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = VeiculosDTO.class))),
                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                })
    public VeiculosDTO update(@RequestBody VeiculosDTO veiculosDTO) {
        return null;
    }

    @DeleteMapping("/{placa}")
    @Operation(summary = "Apaga um veículo através da placa", description = "Busca um veículo pela placa fornecida e se o encontrar, o apaga",
                tags = {"Veiculo"},
                responses = {
                        @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content),
                        @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                })
    public ResponseEntity<?> delete(@PathVariable("placa") String placa) {
        return service.delete(placa);
    }
}
