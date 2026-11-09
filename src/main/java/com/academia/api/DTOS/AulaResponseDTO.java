package com.academia.api.DTOS;

import com.academia.api.Model.Aula;
import com.academia.api.Model.Matricula;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public record AulaResponseDTO(
        Long id,
        String nome,
        LocalDateTime horario,
        Long instrutorId,
        List<Long> matriculasId) {

    public static AulaResponseDTO fromEntity(Aula aula){
        List<Long> idMatriculas =
                aula.getMatriculas() != null ? aula.getMatriculas().stream().map(Matricula::getId).toList() : Collections.emptyList();
        return new AulaResponseDTO(aula.getId(), aula.getNome(), aula.getHorario(),aula.getInstrutor().getId(), idMatriculas);
    }
}
