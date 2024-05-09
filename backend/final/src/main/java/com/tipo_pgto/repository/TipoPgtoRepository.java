package com.tipo_pgto.repository;

import com.tipo_pgto.TipoPgto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPgtoRepository extends JpaRepository<TipoPgto, Integer> {
}
