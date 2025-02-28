package com.manocorbas.alunos.model.dtos;

import com.manocorbas.alunos.model.domain.Aluno;

public record AprovacaoDTO(Aluno aluno, Float media, Boolean aprovado) {
}
