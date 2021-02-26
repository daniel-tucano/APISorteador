package com.zup.desafios.desafiosorteador.services;

import com.zup.desafios.desafiosorteador.models.Aposta;
import com.zup.desafios.desafiosorteador.models.NumerosApostados;
import com.zup.desafios.desafiosorteador.repositories.NumerosApostadosRepository;
import com.zup.desafios.desafiosorteador.utils.Sorteador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NumerosApostadosService {

    @Autowired
    private NumerosApostadosRepository numerosApostadosRepository;

    public List<NumerosApostados> geraNumerosApostados(Aposta aposta) {
        List<Integer> numerosAApostar;
        List<Integer> apostasIguaisRegistradas;

        // Garante que não existem apostas iguais para o mesmo email, gerando sempre uma aposta diferente
        do  {
            numerosAApostar = Sorteador.geraNumerosSorteaveis(6,60);
            apostasIguaisRegistradas = numerosApostadosRepository.apostasWithEmailAndNumerosApostados(aposta.getEmail(), numerosAApostar);
        } while (!apostasIguaisRegistradas.isEmpty());


        return numerosAApostar.stream().map(n -> new NumerosApostados(n,aposta)).collect(Collectors.toList());
    }
}
