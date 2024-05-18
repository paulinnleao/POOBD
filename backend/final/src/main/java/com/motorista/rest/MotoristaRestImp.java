package com.motorista.rest;

import com.motorista.dto.MotoristaDTO;
import com.motorista.service.MotoristaServiceImp;
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
@RequestMapping("/motoristas")
@Tag(name = "Motoristas", description = "Endpoints para gerenciar entidade Motorista.")
public class MotoristaRestImp {

    @Autowired
    private MotoristaServiceImp service;

    @GetMapping(value = "/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Buscar motorista pelo ID", description = "Procura um motorista pelo id. Caso não encontre, retorna um NOT_FOUND",
            tags = {"Motoristas"},
            responses = {
                    @ApiResponse(
                            description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = MotoristaDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public MotoristaDTO findById(@PathVariable("cpf") Long cpf) {
        return service.findById(cpf);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Busca todos os motoristas", description = "Busca todas os motoristas cadastrados no Banco de Dados.",
            tags = {"Motoristas"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                            @Content (
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(
                                            schema = @Schema(
                                                    implementation = MotoristaDTO.class
                                            )
                                    )
                            )}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public List<MotoristaDTO> findAll() {
        return service.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Persiste um novo motorista no Banco de Dados", description = "Persiste um novo motorista no Banco de Dados, caso ele não encontre outro com o mesmo ID.",
            tags = {"Motoristas"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = MotoristaDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content)
            })
    public MotoristaDTO create(@RequestBody MotoristaDTO motoristaDTO) {
        return service.create(motoristaDTO);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualiza os dados de um motorista", description = "Busca o motorista, atualiza, persiste e retorna o motorista atualizado.",
            tags = {"Motoristas"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = MotoristaDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public MotoristaDTO update(@RequestBody MotoristaDTO motoristaDTO) {
        return service.update(motoristaDTO);
    }

    @DeleteMapping("/{cpf}")
    @Operation(summary = "Apaga um motorista através do ID", description = "Busca um motorista pelo ID fornecido e se encontrar, apaga",
            tags = {"Motoristas"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<?> delete(@PathVariable("cpf") Long cpf) {
        return service.delete(cpf);
    }
}
