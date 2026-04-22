package com.arenahub.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO (
        @NotBlank(message = "E-mail obrigatorio")
        @Email(message = "E-mail invalido")
        String email,
        @NotBlank(message = "Senha obrigatoria")
        @Size(min = 6, message = "Senha deve ter no minimo 6 caracteres")
        String password
){
}
