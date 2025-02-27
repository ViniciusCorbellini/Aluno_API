package com.manocorbas.alunos.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.manocorbas.alunos.model.dtos.NotaDTO;
import jakarta.persistence.*;
import lombok.*;


@Table(name = "notas")
@Entity(name = "notas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "id_aluno")
    @JsonBackReference
    private Aluno aluno;

    private String nome;

    private Float valor_nota;

    public Nota(NotaDTO dto, Aluno aluno) {
        this.aluno = aluno;
        this.nome = dto.nome();
        this.valor_nota = dto.valor_nota();
    }
}
