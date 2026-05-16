package com.arenahub.backend.dto;

import java.time.LocalDateTime;

public record EventoResumoDTO(
        Long id,
        String nome,
        LocalDateTime dataInicio,
        String espacoNome,
        Integer ingressosVendidos,
        Double receita
) {}
