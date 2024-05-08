package com.viagens.repository;

import com.viagens.Viagens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViagensRepository extends JpaRepository<Viagens, Long> {
}
