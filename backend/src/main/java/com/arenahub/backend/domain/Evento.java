package com.arenahub.backend.domain;

import com.arenahub.backend.domain.categoria.CategoriaEvento;
import com.arenahub.backend.domain.espaco.Espaco;
import com.arenahub.backend.domain.evento.ClassificacaoIndicativa;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "eventos")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private Integer expectativaPublico;

    @Enumerated(EnumType.STRING)
    private ClassificacaoIndicativa classificacaoIndicativa;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaEvento categoria;

    @ManyToOne
    @JoinColumn(name = "espaco_id")
    private Espaco espaco;

    public Evento() {
    }

    public Evento(String nome, String descricao, LocalDateTime dataInicio, LocalDateTime dataFim,
            Integer expectativaPublico, ClassificacaoIndicativa classificacaoIndicativa, CategoriaEvento categoria,
            Espaco espaco) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.expectativaPublico = expectativaPublico;
        this.classificacaoIndicativa = classificacaoIndicativa;
        this.categoria = categoria;
        this.espaco = espaco;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public Integer getExpectativaPublico() {
        return expectativaPublico;
    }

    public void setExpectativaPublico(Integer expectativaPublico) {
        this.expectativaPublico = expectativaPublico;
    }

    public ClassificacaoIndicativa getClassificacaoIndicativa() {
        return classificacaoIndicativa;
    }

    public void setClassificacaoIndicativa(ClassificacaoIndicativa classificacaoIndicativa) {
        this.classificacaoIndicativa = classificacaoIndicativa;
    }

    public CategoriaEvento getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEvento categoria) {
        this.categoria = categoria;
    }

    public Espaco getEspaco() {
        return espaco;
    }

    public void setEspaco(Espaco espaco) {
        this.espaco = espaco;
    }
}
