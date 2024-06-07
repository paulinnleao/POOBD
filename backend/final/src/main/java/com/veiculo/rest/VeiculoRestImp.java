package com.veiculo.rest;

import com.util.atividades.VeiculoFaturamento;
import com.veiculo.dto.VeiculoDTO;
import com.veiculo.service.VeiculoServiceImp;
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
@RequestMapping("/veiculos")
@Tag(name = "Veiculos", description = "Endpoints para gerenciar entidade Veículos.")
public class VeiculoRestImp {

    @Autowired
    private VeiculoServiceImp service;

    @GetMapping(value = "/{placa}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Buscar veiculo pela PLACA", description = "Procura um veículo pela PLACA. Caso não encontre, retorna um NOT_FOUND",
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
    public VeiculoDTO findById(@PathVariable("placa") String placa) {
        return service.findById(placa);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Busca todos os veículos", description = "Lista todos os veículos cadastrados no Banco de Dados.",
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
    @Operation(summary = "Persiste um novo veículo no Banco de Dados",
            description = "Persiste um novo veículo no Banco de Dados, caso ele não encontre outro com a mesma PLACA." +
                    "Para o caso de encontrar, ele retorna a resposta Conflict",
                tags = {"Veiculos"},
                responses = {
                        @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = VeiculoDTO.class))),
                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
                        @ApiResponse(description = "Conflict", responseCode = "409", content = @Content)
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
    public VeiculoDTO update(@RequestBody VeiculoDTO veiculoDTOAtualizado) {
        return service.update(veiculoDTOAtualizado);
    }

    @DeleteMapping("/{placa}")
    @Operation(summary = "Apaga um veículo através da PLACA", description = "Busca um veículo pela PLACA fornecidA e se encontrar, o apaga",
                tags = {"Veiculos"},
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

    //Fase 02 - atividade 01
    @GetMapping("/{data}/{hora-inicial}/{hora-final}")
    @Operation(summary = "Busca os veículos através da data, hora inicial e hora final.", description = "Busca os veículos com base na data hora fornecidos. Caso não encontre, retorna um NOT_FOUND",
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
    public List<VeiculoDTO> findByDate(@PathParam("data") String data, @PathParam("hora-inicial") String horaInicial, @PathParam("hora-final") String horaFinal){
        return service.findByDate(data, horaInicial, horaFinal);
        }

    //Fase 02 - atividade 01
    @GetMapping("/{mes}")
    @Operation(summary = "Busca os faturamentos de cada veículo do mês fornecido",
            description = "Qual o faturamento de cada veículo no mês, ordenando por proprietário, por tipo de pagto e por veículo",
    tags = {"Veiculos"},
    responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<List<VeiculoFaturamento>> findByMes(@PathParam("mes") Integer mes){
        return service.faturamentoVeiculos(mes);
    }
}
