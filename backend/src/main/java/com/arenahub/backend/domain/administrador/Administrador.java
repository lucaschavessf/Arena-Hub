package com.arenahub.backend.domain.administrador;

import com.arenahub.backend.domain.usuario.User;
import jakarta.persistence.*;

import java.util.Objects;

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