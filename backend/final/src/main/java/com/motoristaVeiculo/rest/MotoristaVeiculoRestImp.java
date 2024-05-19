package com.motoristaVeiculo.rest;

import com.motoristaVeiculo.dto.MotoristaVeiculoDTO;
import com.motoristaVeiculo.service.MotoristaVeiculoServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
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

    @GetMapping(value = "/id", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Buscar motoristaVeiculo pelo ID",
            description = "Procura um motoristaVeiculo pelo ID." +
                    " O ID é composto pelo CPF do motorista e a Placa do veículo." +
                    " Caso não encontre, retorna uma resposta NOT_FOUND",
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
    public MotoristaVeiculoDTO findById(
            @PathParam("cpfMotorista") Long cpfMotorista,
            @PathParam("placa") String placa
    ) {
        return service.findById(cpfMotorista, placa);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Busca todos os motoristaVeiculos", description = "Lista todas os motoristaVeiculos cadastrados no Banco de Dados.",
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
    @Operation(summary = "Persiste um novo motoristaVeiculo no Banco de Dados",
            description = "Persiste um novo motoristaVeiculo no Banco de Dados, caso ele não encontre outro com o mesmo ID. " +
                    "Para o caso de encontrar, ele retorna a resposta Conflict",
            tags = {"Motoristas Veiculos"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = MotoristaVeiculoDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content)
            })
    public MotoristaVeiculoDTO create(@RequestBody MotoristaVeiculoDTO motoristaVeiculoDTO) {
        return service.create(motoristaVeiculoDTO);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualiza os dados de um motoristaVeiculo",
            description = "Como se trata de uma chave primária composta e essas chaves também são estrangeiras." +
                    " Será persistido um novo campo, caso não exista, para essa atualização e apagado o anterior. " +
                    "Para o caso de encontrar, ele retorna a resposta Conflict",
            tags = {"Motoristas Veiculos"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = MotoristaVeiculoDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content)
            })
    public MotoristaVeiculoDTO update(@RequestBody MotoristaVeiculoDTO motoristaVeiculoDTO, @PathParam("novaPlaca") String novaPlaca) {
        return service.update(motoristaVeiculoDTO, novaPlaca);
    }

    @DeleteMapping("/id")
    @Operation(summary = "Apaga um motoristaVeiculo através do ID", description = "Busca um motoristaVeiculo pelo ID fornecido e se encontrar, o apaga",
            tags = {"Motoristas Veiculos"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<?> delete(
            @PathParam("cpfMotorista") Long cpfMotorista,
            @PathParam("placa") String placa) {
        return service.delete(cpfMotorista, placa);
    }
}
