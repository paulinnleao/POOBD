package com.util.atividades;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ViagensMediaMensalSexo {

    private String mes;
    private Double mediaMasculino;
    private Double mediaFeminino;

    public ViagensMediaMensalSexo(String mes, Double mediaMasculino){
        this.mes = mes;
        this.mediaMasculino = mediaMasculino;
    }
}

