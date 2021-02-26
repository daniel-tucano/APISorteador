package com.zup.desafios.desafiosorteador.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "apostas")
@Data
public class Aposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "sorteio_id")
    private Sorteio sorteio;

    @Email
    @NotNull
    private String email;

    @OneToMany( mappedBy = "aposta",cascade= CascadeType.ALL)
    @Size(max = 6, min = 6)
    private List<NumerosApostados> numerosApostados;
}
