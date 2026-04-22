package com.arenahub.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;

public record EventoRequestDTO(
    @NotBlank(message = "Nome obrigatorio")
    String nome,
    @NotBlank(message = "Descricao obrigatoria")
    String descricao,
    @NotNull(message = "Data de inicio obrigatoria")
    LocalDateTime dataInicio,
    @NotNull(message = "Data de fim obrigatoria")
    LocalDateTime dataFim,
    @NotNull(message = "Expectativa de publico obrigatoria")
    @Positive(message = "Expectativa de publico deve ser maior que zero")
    Integer expectativaPublico,
    @NotBlank(message = "Categoria obrigatoria")
    String categoria,
    @NotNull(message = "Espaco obrigatorio")
    @Positive(message = "Espaco deve ser maior que zero")
    Long espacoId
) {}
