package com.motoristaVeiculo.repository;

import com.motoristaVeiculo.MotoristaVeiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotoristaVeiculoRepository extends JpaRepository<MotoristaVeiculo, MotoristaVeiculo.MotoristaVeiculoId> {
}
