package com.manocorbas.alunos.repositories;

import com.manocorbas.alunos.model.domain.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotaRepository extends JpaRepository<Nota, Long> {
    //TODO: comittar esse update
    Optional<Nota> findNotaById(Long id);
    Optional<Nota> findNotaByNome(String nome);
}
