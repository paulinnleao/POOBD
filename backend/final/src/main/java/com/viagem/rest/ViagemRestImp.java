package com.viagem.rest;

import com.util.atividades.ViagensMediaMensalSexo;
import com.viagem.dto.ViagemDTO;
import com.viagem.service.ViagemServiceImp;
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

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/viagens")
@Tag(name = "Viagens", description = "Endpoints para gerenciar entidade Viagem.")
public class ViagemRestImp {

    @Autowired
    private ViagemServiceImp service;

    @GetMapping(value = "/id", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Buscar viagem pelo ID",
            description = "Procura uma viagem pelo ID. " +
                    "O ID é uma chave composta por: CPF do passageiro, CPF do motorista, Placa e Data hora inicio da viagem." +
                    " Caso não encontre, retorna uma resposta NOT_FOUND",
            tags = {"Viagens"},
            responses = {
                    @ApiResponse(
                            description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = ViagemDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ViagemDTO findById(
            @PathParam("cpfPassag")Long cpfPassag,
            @PathParam("cpfMotorista") Long cpfMotorista,
            @PathParam("placa") String placa,
            @PathParam("dtHoraInicio") String dtHoraInicio
            ) {
        return service.findById(cpfPassag, cpfMotorista, placa, dtHoraInicio);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Busca todos as viagens", description = "Lista todas as viagens cadastradas no Banco de Dados.",
            tags = {"Viagens"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                            @Content (
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(
                                            schema = @Schema(
                                                    implementation = ViagemDTO.class
                                            )
                                    )
                            )}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public List<ViagemDTO> findAll() {
        return service.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Persiste uma nova viagem no Banco de Dados",
            description = "Persiste uma nova viagem no Banco de Dados, caso ele não encontre outro com o mesmo ID." +
                    "Para o caso de encontrar, ele retorna a resposta Conflict",
            tags = {"Viagens"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = ViagemDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content)
            })
    public ViagemDTO create(@RequestBody ViagemDTO viagemDTO) {
        return service.create(viagemDTO);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualiza os dados de uma viagem", description = "Busca a viagem, atualiza, persiste e retorna a viagem atualizada.",
            tags = {"Viagens"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = ViagemDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ViagemDTO update(@RequestBody ViagemDTO viagemDTO) {
        return service.update(viagemDTO);
    }

    @DeleteMapping("/id")
    @Operation(summary = "Apaga uma viagem através do ID", description = "Busca uma viagem pelo ID fornecido e se encontrar, apaga. " +
            "O ID é uma chave composta por: CPF do passageiro, CPF do motorista, Placa e Data hora inicio da viagem.",
            tags = {"Viagens"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<?> delete(
            @PathParam("cpfPassag")Long cpfPassag,
            @PathParam("cpfMotorista") Long cpfMotorista,
            @PathParam("placa") String placa,
            @PathParam("dtHoraInicio") String dtHoraInicio
    ) {
        return service.delete(placa, cpfPassag, cpfMotorista, dtHoraInicio);
    }

    @GetMapping("/{quantidade}/{mes}")
    @Operation(summary = "Buscar faturamento de um determinado mês.",
            description = "Busca o faturamento de um determinado mês. É necessário passar a quantidade que deseja e o mês.",
            tags = {"Viagens"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public List<ViagemDTO> faturamentoPorMes(@PathParam("quantidade") Integer quantidade, @PathParam("mes") Integer mes){
        return service.faturamentoPorMes(quantidade, mes);
    }

    @GetMapping("/media-mensal-por-mes")
    @Operation(summary = "Buscar media mensal de cada sexo",
            description = "Busca a média de cada sexo de cada mês. Não é necessário passar nada como parâmetro",
            tags = {"Viagens"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<List<ViagensMediaMensalSexo>> buscarViagensMediaMensalSexo(){
        return service.buscarViagensMediaMensalSexo();
    }
}
