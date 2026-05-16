package com.arenahub.backend.dto;

import java.math.BigDecimal;

public record LoteItemResponseDTO(
    Long id,
    Long categoriaId,
    String categoriaNome,
    BigDecimal preco,
    int quantidadeTotal,
    int vendidos,
    int disponiveis
) {}
