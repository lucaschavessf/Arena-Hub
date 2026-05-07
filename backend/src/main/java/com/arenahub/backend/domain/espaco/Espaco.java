package com.arenahub.backend.domain.espaco;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "espacos")
public class Espaco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int capacidade;
    private String localizacao;

    @Enumerated(EnumType.STRING)
    private TipoCobranca tipoCobranca;

    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    private StatusEspaco status;

    public Espaco() {
    }

    public Espaco(String nome, int capacidade, String localizacao, TipoCobranca tipoCobranca, BigDecimal preco,
            StatusEspaco status) {
        this.nome = nome;
        this.capacidade = capacidade;
        this.localizacao = localizacao;
        this.tipoCobranca = tipoCobranca;
        this.preco = preco;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public TipoCobranca getTipoCobranca() {
        return tipoCobranca;
    }

    public void setTipoCobranca(TipoCobranca tipoCobranca) {
        this.tipoCobranca = tipoCobranca;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public StatusEspaco getStatus() {
        return status;
    }

    public void setStatus(StatusEspaco status) {
        this.status = status;
    }
}
