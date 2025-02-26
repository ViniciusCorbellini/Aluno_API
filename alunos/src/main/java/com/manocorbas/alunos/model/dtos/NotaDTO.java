package com.manocorbas.alunos.model.dtos;

import com.manocorbas.alunos.model.domain.Aluno;

public record NotaDTO(Long id_aluno, String nome, Float valor_nota) {}
