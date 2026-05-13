package com.arenahub.backend.dto;

import com.arenahub.backend.domain.evento.ClassificacaoIndicativa;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record SolicitacaoEventoRequestDTO(
        @NotBlank(message = "Nome do evento e obrigatorio")
        @Size(min = 3, max = 120, message = "Nome do evento deve ter entre 3 e 120 caracteres")
        String nomeEvento,

        @NotBlank(message = "Descricao e obrigatoria")
        @Size(min = 10, max = 2000, message = "Descricao deve ter entre 10 e 2000 caracteres")
        String descricao,

        @NotNull(message = "Data de inicio e obrigatoria")
        LocalDateTime dataInicio,

        @NotNull(message = "Data de fim e obrigatoria")
        LocalDateTime dataFim,

        @NotNull(message = "Expectativa de publico e obrigatoria")
        @Positive(message = "Expectativa de publico deve ser maior que zero")
        Integer expectativaPublico,

        @NotNull(message = "Classificacao indicativa e obrigatoria")
        ClassificacaoIndicativa classificacaoIndicativa,

        @NotNull(message = "Categoria e obrigatoria")
        Long categoriaId,

        @NotNull(message = "Espaco e obrigatorio")
        Long espacoId,

        @NotBlank(message = "Nome do solicitante e obrigatorio")
        @Size(min = 3, max = 120, message = "Nome do solicitante deve ter entre 3 e 120 caracteres")
        String nomeSolicitante,

        @NotBlank(message = "E-mail do solicitante e obrigatorio")
        @Email(message = "E-mail do solicitante invalido")
        String emailSolicitante) {
}
