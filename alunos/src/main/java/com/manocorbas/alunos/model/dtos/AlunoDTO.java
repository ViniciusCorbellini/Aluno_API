package com.manocorbas.alunos.model.dtos;

import java.util.List;

public record AlunoDTO(String nome, String matricula, List<NotaDTO> notas) {
}
