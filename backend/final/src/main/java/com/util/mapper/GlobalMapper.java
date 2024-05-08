package com.util.mapper;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class GlobalMapper {
    private static ModelMapper mapper = new ModelMapper();

    public static <O, D> D parseObject (O origem, Class<D> destino){
        return mapper.map(origem, destino);
    }

    public static <O, D>List<D> parseListObject (List<O> listaOrigem, Class<D> destino){
        List<D> destinoList = new ArrayList<D>();
        listaOrigem.forEach(
                objeto -> destinoList.add(
                        mapper.map(objeto, destino)
                )
        );
        return destinoList;
    }
}
