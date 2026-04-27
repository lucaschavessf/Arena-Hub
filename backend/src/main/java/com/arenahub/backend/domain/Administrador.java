package com.arenahub.backend.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "administradores")
@DiscriminatorValue("ADMIN")
public class Administrador extends User
{

    @Column(name = "setor_departamento")
    private String setorDepartamento;

    public Administrador(){}

    public Administrador(String setorDepartamento){this.setorDepartamento = setorDepartamento;}

    public String getSetorDepartamento(){return setorDepartamento;}

    public void setSetorDepartamento(String setorDepartamento){this.setorDepartamento = setorDepartamento;}
}