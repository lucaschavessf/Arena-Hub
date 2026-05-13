package com.arenahub.backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtores")
@DiscriminatorValue("PRODUTOR")
public class Produtor extends User {

    @Column(nullable = false, unique = true, length = 14)
    private String cnpj;

    public Produtor() {}

    public Produtor(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
