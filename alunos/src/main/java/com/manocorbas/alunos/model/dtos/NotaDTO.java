package com.manocorbas.alunos.model.dtos;

import com.manocorbas.alunos.model.domain.Aluno;

public record NotaDTO(Aluno aluno, String nome, Float valor) {}
