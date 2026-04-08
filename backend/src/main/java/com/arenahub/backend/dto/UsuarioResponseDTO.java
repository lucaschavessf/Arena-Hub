package com.arenahub.backend.dto;

public class UsuarioResponseDTO {

    private String id;
    private String nome;
    private String email;
    private String tipo;
    private String setorDepartamento;
    private String cpf;

    public UsuarioResponseDTO(String id, String nome, String email, String tipo, String setorDepartamento, String cpf) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.tipo = tipo;
        this.setorDepartamento = setorDepartamento;
        this.cpf = cpf;
    }

    public UsuarioResponseDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSetorDepartamento() {
        return setorDepartamento;
    }

    public void setSetorDepartamento(String setorDepartamento) {
        this.setorDepartamento = setorDepartamento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}