package com.arenahub.backend.dto;

import java.time.LocalDateTime;

public record EventoRequestDTO(
    String nome,
    String descricao,
    LocalDateTime dataInicio,
    LocalDateTime dataFim,
    Integer expectativaPublico,
    String categoria,
    Long espacoId
) {}
