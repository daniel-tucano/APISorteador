package com.zup.desafios.desafiosorteador.DTOs;

import com.zup.desafios.desafiosorteador.models.Aposta;
import com.zup.desafios.desafiosorteador.models.NumerosApostados;
import com.zup.desafios.desafiosorteador.models.Sorteio;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ApostaDTO {

    private Integer id;

    private String email;

    private Sorteio sorteio;

    private List<Integer> numerosApostados;

    public  ApostaDTO(Aposta aposta) {
        this.id = aposta.getId();
        this.email = aposta.getEmail();
        this.sorteio = aposta.getSorteio();
        this.numerosApostados = aposta.getNumerosApostados().stream().map(NumerosApostados::getNumeroApostado).collect(Collectors.toList());
    }
}
