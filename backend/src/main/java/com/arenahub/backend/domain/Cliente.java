package com.arenahub.backend.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
@DiscriminatorValue("CLIENTE")
public class Cliente extends User
{

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    public Cliente(){}

    public Cliente(String cpf){
        this.cpf = cpf;
    }

    public String getCpf(){
        return cpf;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }
}