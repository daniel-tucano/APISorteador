package com.zup.desafios.desafiosorteador.utils;

import java.util.ArrayList;
import java.util.List;

public class Sorteador {

    public static Integer geraNumeroSorteavel(Integer maxValue) {
        return (int) Math.ceil(Math.random()*maxValue);
    }

    public static List<Integer> geraNumerosSorteaveis(Integer size, Integer maxValue) {
        ArrayList<Integer> numerosSorteaveis = new ArrayList<Integer>();
        for (int i = 1; i <= size; i++) {
            Integer numeroSorteavel;
            do  {
                numeroSorteavel = geraNumeroSorteavel(maxValue);
            } while (numerosSorteaveis.contains(numeroSorteavel));
            numerosSorteaveis.add(numeroSorteavel);
        }
        return numerosSorteaveis;
    }
}
