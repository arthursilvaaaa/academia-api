package com.academia.api.DTOS;

import com.academia.api.Model.Aluno;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record AlunoResponseDTO(Long id,
                               String nome,
                               String email,
                               LocalDate dataNascimento,
                               List<Long> matriculasId) {

    public static AlunoResponseDTO fromEntity(Aluno aluno){
        List<Long> matriculasId =
                aluno.getMatriculas() != null ? aluno.getMatriculas().stream().map(matricula -> matricula.getId()).toList() : Collections.emptyList();
        return new AlunoResponseDTO(aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getDataNascimento(), matriculasId);
    }
}
