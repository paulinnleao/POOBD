package com.viagem.repository;

import com.util.atividades.ViagensMediaMensalSexo;
import com.viagem.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ViagemRepository extends JpaRepository<Viagem, Viagem.ViagemId> {

    @Query(value = "SELECT V.* \n" +
            "FROM VIAGENS V \n" +
            "WHERE V.DT_HORA_INICIO BETWEEN :dtHoraInicio AND :dtHoraFim \n" +
            "  AND V.DT_HORA_FIM BETWEEN :dtHoraInicio AND :dtHoraFim\n", nativeQuery = true)
    List<Viagem> findByDate(@Param("dtHoraInicio") LocalDateTime dtHoraInicio, @Param("dtHoraFim") LocalDateTime dtHoraFim);

    //@Query(value = "SELECT TOP :quantidade * FROM VIAGENS WHERE EXTRACT (MONTH FROM DT_HORA_INICIO) = :mes", nativeQuery = true)
    // Alteração feita pela Atividade 03
    @Query(value = "SELECT * FROM VIAGENS WHERE EXTRACT(MONTH FROM DT_HORA_INICIO) = :mes LIMIT :quantidade", nativeQuery = true)
    List<Viagem> faturamentoPorMesEQuantidade(@Param("quantidade") Integer quantidade, @Param("mes") Integer mes);

    @Query(value = "SELECT * FROM VIAGENS WHERE EXTRACT(MONTH FROM DT_HORA_INICIO) = :mes", nativeQuery = true)
    List<Viagem> faturamentoPorMes(@Param("mes") Integer mes);

    // Fase 02 - atividade 04
            // Primeiro script teste
            //    @Query(value = "SELECT MONTH(DT_HORA_INICIO) AS mes AVG(VALOR_PAGTO) as feminino FROM VIAGENS V" +
            //            "RIGHT JOIN PESSOAS P ON P.CPF_PESSOA = V.CPF_PASSAG AND P.SEXO = 'F'" +
            //            "GROUP BY Mes", nativeQuery = true)
    @Query(value =
            "SELECT " +
                "EXTRACT(MONTH FROM V.DT_HORA_INICIO) AS mes, " +
                "P.SEXO AS sexo, " +
                "AVG(V.VALOR_PAGTO) AS media " +
            "FROM " +
                "VIAGENS V " +
            "RIGHT JOIN " +
                "PESSOAS P ON P.CPF_PESSOA = V.CPF_PASSAG " +
            "GROUP BY " +
                "mes, " +
                "sexo " +
            "ORDER BY " +
                "1",
            nativeQuery = true)
    List<Object[]> buscarViagensMediaMensalSexo();
}

