package com.manocorbas.alunos.repositories;

import com.manocorbas.alunos.model.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Optional<Aluno> findAlunoById(Long id);
    Optional<Aluno> findAlunoByMatricula(String matricula);
}
