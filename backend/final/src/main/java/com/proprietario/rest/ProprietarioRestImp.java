package com.proprietario.rest;

import com.proprietario.dto.ProprietarioDTO;
import com.proprietario.service.ProprietarioServiceImp;
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
@RequestMapping("/proprietarios")
@Tag(name = "Proprietários", description = "Endpoints para gerenciar entidade Proprietario.")
public class ProprietarioRestImp {


    @Autowired
    private ProprietarioServiceImp service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Busca todos os proprietários", description = "Lista todos os proprietários cadastrados no Banco de Dados.",
            tags = {"Proprietários"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(
                                            schema = @Schema(
                                                    implementation = ProprietarioDTO.class
                                            )
                                    )
                            )}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public List<ProprietarioDTO> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Buscar proprietário pelo CPF", description = "Procura um proprietário pelo CPF. Caso não encontre, retorna uma resposta NOT_FOUND",
            tags = {"Proprietários"},
            responses = {
                    @ApiResponse(
                            description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = ProprietarioDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ProprietarioDTO findById(@PathVariable("cpf") Long cpf) {
        return service.findById(cpf);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Persiste um novo proprietário no Banco de Dados",
            description = "Persiste um novo proprietário no Banco de Dados, caso ele não encontre outro com o mesmo CPF." +
                    "Para o caso de encontrar, ele retorna a resposta Conflict",
            tags = {"Proprietários"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = ProprietarioDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content)
            })
    public ProprietarioDTO create(@RequestBody ProprietarioDTO proprietarioDTO) {
        return service.create(proprietarioDTO);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualiza os dados de um proprietário", description = "Busca o proprietário, atualiza, persiste e retorna o proprietário atualizado.",
            tags = {"Proprietários"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = ProprietarioDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ProprietarioDTO update(@RequestBody ProprietarioDTO proprietarioDTO) {
        return service.update(proprietarioDTO);
    }

    @DeleteMapping("/{cpf}")
    @Operation(summary = "Apaga um proprietário através do CPF", description = "Busca um proprietário pelo CPF fornecido e se o encontrar, o apaga",
            tags = {"Proprietários"},
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
