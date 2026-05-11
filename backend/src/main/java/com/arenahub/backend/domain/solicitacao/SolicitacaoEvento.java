package com.arenahub.backend.domain.solicitacao;

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
@Table(name = "solicitacoes_evento")
public class SolicitacaoEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeEvento;
    private String descricao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private Integer expectativaPublico;

    @Enumerated(EnumType.STRING)
    private ClassificacaoIndicativa classificacaoIndicativa;

    @Enumerated(EnumType.STRING)
    private StatusSolicitacaoEvento status;

    private String motivoRejeicao;
    private LocalDateTime criadaEm;
    private LocalDateTime analisadaEm;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaEvento categoria;

    @ManyToOne
    @JoinColumn(name = "espaco_id")
    private Espaco espaco;

    private String nomeSolicitante;
    private String emailSolicitante;

    public SolicitacaoEvento() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
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

    public StatusSolicitacaoEvento getStatus() {
        return status;
    }

    public void setStatus(StatusSolicitacaoEvento status) {
        this.status = status;
    }

    public String getMotivoRejeicao() {
        return motivoRejeicao;
    }

    public void setMotivoRejeicao(String motivoRejeicao) {
        this.motivoRejeicao = motivoRejeicao;
    }

    public LocalDateTime getCriadaEm() {
        return criadaEm;
    }

    public void setCriadaEm(LocalDateTime criadaEm) {
        this.criadaEm = criadaEm;
    }

    public LocalDateTime getAnalisadaEm() {
        return analisadaEm;
    }

    public void setAnalisadaEm(LocalDateTime analisadaEm) {
        this.analisadaEm = analisadaEm;
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

    public String getNomeSolicitante() {
        return nomeSolicitante;
    }

    public void setNomeSolicitante(String nomeSolicitante) {
        this.nomeSolicitante = nomeSolicitante;
    }

    public String getEmailSolicitante() {
        return emailSolicitante;
    }

    public void setEmailSolicitante(String emailSolicitante) {
        this.emailSolicitante = emailSolicitante;
    }
}
