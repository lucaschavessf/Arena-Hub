package com.arenahub.backend.dto;

import com.arenahub.backend.domain.evento.ClassificacaoIndicativa;
import com.arenahub.backend.domain.solicitacao.StatusSolicitacaoEvento;

import java.time.LocalDateTime;

public class SolicitacaoEventoResponseDTO {

    private Long id;
    private String nomeEvento;
    private String descricao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private Integer expectativaPublico;
    private ClassificacaoIndicativa classificacaoIndicativa;
    private StatusSolicitacaoEvento status;
    private String motivoRejeicao;
    private LocalDateTime criadaEm;
    private LocalDateTime analisadaEm;
    private Long categoriaId;
    private String categoriaNome;
    private Long espacoId;
    private String espacoNome;
    private String nomeSolicitante;
    private String emailSolicitante;

    public SolicitacaoEventoResponseDTO() {
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

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getCategoriaNome() {
        return categoriaNome;
    }

    public void setCategoriaNome(String categoriaNome) {
        this.categoriaNome = categoriaNome;
    }

    public Long getEspacoId() {
        return espacoId;
    }

    public void setEspacoId(Long espacoId) {
        this.espacoId = espacoId;
    }

    public String getEspacoNome() {
        return espacoNome;
    }

    public void setEspacoNome(String espacoNome) {
        this.espacoNome = espacoNome;
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
