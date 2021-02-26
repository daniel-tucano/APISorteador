package com.zup.desafios.desafiosorteador.repositories;

import com.zup.desafios.desafiosorteador.models.Sorteio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SorteioRepository extends JpaRepository<Sorteio, Integer> {

    @Query(nativeQuery = true, value = "SELECT * " +
            "FROM sorteador.sorteios " +
            "WHERE dia_do_sorteio > current_date() " +
            "ORDER BY dia_do_sorteio DESC " +
            "LIMIT 1")
    Optional<Sorteio> findLatestSorteio();
}
