package com.arenahub.backend.dto;

import java.time.LocalDateTime;
import java.util.List;

public record EventoResponseDTO(
    Long id,
    String nome,
    String descricao,
    LocalDateTime dataInicio,
    LocalDateTime dataFim,
    Integer expectativaPublico,
    String categoriaNome,
    String espacoNome,
    String classificacaoIndicativa,
    String status,
    List<LoteResponseDTO> lotes
) {}
