package com.arenahub.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RejeitarSolicitacaoEventoRequestDTO(
        @NotBlank(message = "Motivo e obrigatorio")
        @Size(min = 5, max = 500, message = "Motivo deve ter entre 5 e 500 caracteres")
        String motivo) {
}
