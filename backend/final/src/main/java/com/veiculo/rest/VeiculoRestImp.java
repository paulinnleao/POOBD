package com.veiculo.rest;

import com.veiculo.dto.VeiculoDTO;
import com.veiculo.service.VeiculoServiceImp;
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
@RequestMapping("/veiculos")
@Tag(name = "Veiculos", description = "Endpoints para gerenciar entidade Veículos.")
public class VeiculoRestImp {

    @Autowired
    private VeiculoServiceImp service;

    @GetMapping(value = "/{id_veiculo}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Buscar veiculo pelo id_veiculo", description = "Procura um veículo pelo id_veiculo. Caso não encontre, retorna um NOT_FOUND",
                tags = {"Veiculos"},
                responses = {
                    @ApiResponse(
                            description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = VeiculoDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                })
    public VeiculoDTO findById(@PathVariable("id_veiculo") Long id_veiculo) {
        return service.findById(id_veiculo);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Busca todos os veículos", description = "Busca todos os veículos cadastrados no Banco de Dados.",
                tags = {"Veiculos"},
                responses = {
                        @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                                @Content (
                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                        array = @ArraySchema(
                                                schema = @Schema(
                                                        implementation = VeiculoDTO.class
                                                )
                                        )
                                )}),
                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                })
    public List<VeiculoDTO> findAll() {
        return service.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Persiste um novo veículo no Banco de Dados", description = "Persiste um novo veículo no Banco de Dados, caso ele não encontre outro com o mesmo ID.",
                tags = {"Veiculos"},
                responses = {
                        @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = VeiculoDTO.class))),
                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                })
    public VeiculoDTO create(@RequestBody VeiculoDTO veiculoDTO) {
        return service.create(veiculoDTO);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualiza os dados de um veículo", description = "Busca o veículo, atualiza, persiste e retorna o veículo atualizado.",
                tags = {"Veiculos"},
                responses = {
                        @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = VeiculoDTO.class))),
                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                })
    public VeiculoDTO update(@RequestBody VeiculoDTO veiculoDTO) {
        return service.update(veiculoDTO);
    }

    @DeleteMapping("/{id_veiculo}")
    @Operation(summary = "Apaga um veículo através do id_veiculo", description = "Busca um veículo pelo id_veiculo fornecido e se o encontrar, o apaga",
                tags = {"Veiculos"},
                responses = {
                        @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content),
                        @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
                })
    public ResponseEntity<?> delete(@PathVariable("id_veiculo") Long id_veiculo) {
        return service.delete(id_veiculo);
    }
}
