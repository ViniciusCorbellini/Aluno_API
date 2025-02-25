package com.manocorbas.alunos.model.domain;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "notas")
@Entity(name = "notas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    private String nome;

    private float valor_nota;
}
