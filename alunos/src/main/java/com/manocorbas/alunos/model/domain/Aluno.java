package com.manocorbas.alunos.model.domain;

import com.manocorbas.alunos.model.dtos.AlunoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "alunos")
@Entity(name = "alunos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String matricula;

    @OneToMany(mappedBy = "alunos", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Nota> notas;

    public Aluno(AlunoDTO dto) {
        this.nome = dto.nome();
        this.notas = dto.notas();
        this.matricula = dto.matricula();
    }
}
