package com.arenahub.backend.dto;

import java.util.List;
import java.time.LocalDateTime;

public record LoteBlocoResponseDTO(
    String nome,
    String status,
    LocalDateTime dataInicio,
    LocalDateTime dataFim,
    List<LoteItemResponseDTO> itens
) {}
