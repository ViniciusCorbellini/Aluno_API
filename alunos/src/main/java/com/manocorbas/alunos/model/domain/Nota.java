package com.manocorbas.alunos.model.domain;

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
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    private String nome;

    private float valor_nota;

    public Nota(NotaDTO dto) {
        this.aluno = dto.aluno();
        this.nome = dto.nome();
        this.valor_nota = dto.valor();
    }
}
