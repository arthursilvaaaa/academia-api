package com.academia.api.DTOS;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AlunoRequestDTO(@NotBlank(message = "Não pode estar vazio.")
                              String nome,
                              @NotBlank(message = "Não pode estar vazio.")
                              String email,
                              @NotNull(message = "Não pode estar vazio.")
                              LocalDate dataNascimento){
}
