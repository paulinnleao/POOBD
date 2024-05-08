package com.veiculos.repository;

import com.veiculos.Veiculos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculosRepository extends JpaRepository<Veiculos, String> {
}
