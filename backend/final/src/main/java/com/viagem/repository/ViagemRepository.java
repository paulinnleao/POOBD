package com.viagem.repository;

import com.viagem.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ViagemRepository extends JpaRepository<Viagem, Viagem.ViagemId> {

    @Query(value = "SELECT V.* FROM VIAGENS V WHERE V.DT_HORA_INICIO = :dtHoraInicio AND v.DT_HORA_FIM = :dtHoraFim", nativeQuery = true)
    List<Viagem> findByDate(@Param("dtHoraInicio") LocalDateTime dtHoraInicio, @Param("dtHoraFim") LocalDateTime dtHoraFim);
}

