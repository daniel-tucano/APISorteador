package com.zup.desafios.desafiosorteador.repositories;

import com.zup.desafios.desafiosorteador.models.NumerosApostados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NumerosApostadosRepository extends JpaRepository<NumerosApostados, Integer> {
    @Query("SELECT count(n.numeroApostado) " +
            "FROM NumerosApostados n " +
            "INNER JOIN n.aposta a " +
            "WHERE n.numeroApostado IN :numerosApostados " +
            "AND a.email = :email " +
            "GROUP BY a.id " +
            "HAVING count(n.numeroApostado) = 6")
    List<Integer> apostasWithEmailAndNumerosApostados( String email, List<Integer> numerosApostados);
}
