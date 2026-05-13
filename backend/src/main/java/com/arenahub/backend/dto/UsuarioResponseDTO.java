package com.arenahub.backend.dto;

public class UsuarioResponseDTO {
    private String id;
    private String name;
    private String email;
    private String tipo;
    private String setorDepartamento;
    private String cpf;
    private String cnpj;

    public UsuarioResponseDTO(String id, String name, String email, String tipo, String setorDepartamento, String cpf, String cnpj) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.tipo = tipo;
        this.setorDepartamento = setorDepartamento;
        this.cpf = cpf;
        this.cnpj = cnpj;
    }

    public UsuarioResponseDTO() {
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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
        return name;
    }

    public void setNome(String name) {
        this.name = name;
    }
}