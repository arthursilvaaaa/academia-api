package com.academia.api.DTOS;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AulaRequestDTO(
                             @NotBlank(message = "O nome não pode estar vazio")
                             String nome,
                             @NotNull(message = "O horário não pode estar vazio")
                             LocalDateTime horario,
                             @NotNull(message = "O ID do instrutor não pode estar vazio")
                             Long instrutorId) {
}
