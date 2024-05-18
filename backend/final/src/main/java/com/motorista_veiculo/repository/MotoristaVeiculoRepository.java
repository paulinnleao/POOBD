package com.motorista_veiculo.repository;

import com.motorista_veiculo.MotoristaVeiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotoristaVeiculoRepository extends JpaRepository<MotoristaVeiculo, MotoristaVeiculo.MotoristaVeiculoId> {
}
