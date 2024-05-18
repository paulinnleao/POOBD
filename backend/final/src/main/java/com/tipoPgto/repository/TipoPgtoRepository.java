package com.tipoPgto.repository;

import com.tipoPgto.TipoPgto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPgtoRepository extends JpaRepository<TipoPgto, Integer> {
}
