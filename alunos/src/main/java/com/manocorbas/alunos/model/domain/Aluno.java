package com.manocorbas.alunos.model.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.manocorbas.alunos.model.dtos.AlunoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "alunos")
@Entity(name = "alunos")
@Getter
@Setter
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

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Nota> notas;

    public Aluno(AlunoDTO dto) {
        this.nome = dto.nome();
        this.notas = dto.notas().stream().map(notaDTO -> new Nota(notaDTO, this)).toList();
        this.matricula = dto.matricula();
    }
}
