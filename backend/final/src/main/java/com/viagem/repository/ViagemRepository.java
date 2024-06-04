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

    //@Query(value = "SELECT TOP :quantidade * FROM VIAGENS WHERE EXTRACT (MONTH FROM DT_HORA_INICIO) = :mes", nativeQuery = true)
    // Alteração feita pela Atividade 03
//    @Query(value = "SELECT * FROM VIAGENS WHERE EXTRACT(MONTH FROM DT_HORA_INICIO) = :mes LIMIT :quantidade", nativeQuery = true)
//    List<Viagem> faturamentoPorMes(@Param("quantidade") Integer quantidade, @Param("mes") Integer mes);
    @Query(value = "SELECT * FROM VIAGENS WHERE EXTRACT(MONTH FROM DT_HORA_INICIO) = :mes " +
            "LIMIT CASE WHEN :quantidade IS NULL THEN CAST('infinity' AS bigint) ELSE :quantidade END",
            nativeQuery = true)
    List<Viagem> faturamentoPorMes(@Param("quantidade") Integer quantidade, @Param("mes") Integer mes);

}

