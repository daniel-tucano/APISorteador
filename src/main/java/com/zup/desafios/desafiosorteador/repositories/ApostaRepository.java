package com.zup.desafios.desafiosorteador.repositories;

import com.zup.desafios.desafiosorteador.models.Aposta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApostaRepository extends JpaRepository<Aposta, Integer> {
    Page<Aposta> findAllByEmail(String email, Pageable pageable);

    @Query("SELECT a " +
            "FROM Aposta a " +
            "JOIN FETCH a.numerosApostados " +
            "WHERE a IN :apostas " +
            "AND a.email = :email")
    List<Aposta> findApostasNumerosApostadosByEmail(String email, List<Aposta> apostas);

    @Query("SELECT a " +
            "FROM Aposta a " +
            "JOIN FETCH a.numerosApostados " +
            "WHERE a IN :apostas")
    List<Aposta> findApostasNumerosApostados(List<Aposta> apostas);
}
