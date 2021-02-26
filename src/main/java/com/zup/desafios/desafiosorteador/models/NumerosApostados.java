package com.zup.desafios.desafiosorteador.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

@Entity
@Table( name = "numeros_apostados")
@Data
public class NumerosApostados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Range(min = 1, max = 60)
    private Integer numeroApostado;

    @ManyToOne
    @JoinColumn(name = "aposta_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Aposta aposta;

    public NumerosApostados() {
    }

    public NumerosApostados(Integer numeroApostado, Aposta aposta) {
        this.numeroApostado = numeroApostado;
        this.aposta = aposta;
    }
}
