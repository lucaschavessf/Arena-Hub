package com.arenahub.backend.dto;

import java.math.BigDecimal;

public record LoteItemRequestDTO(
    Long categoriaId,
    BigDecimal preco,
    int quantidadeTotal
) {}
