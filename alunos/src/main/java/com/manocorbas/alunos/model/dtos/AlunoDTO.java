package com.manocorbas.alunos.model.dtos;

import com.manocorbas.alunos.model.domain.Nota;

import java.util.List;

public record AlunoDTO(String nome, String matricula, List<Nota> notas) {
}
