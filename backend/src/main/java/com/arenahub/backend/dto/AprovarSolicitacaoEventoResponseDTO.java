package com.arenahub.backend.dto;

public class AprovarSolicitacaoEventoResponseDTO {

    private SolicitacaoEventoResponseDTO solicitacao;
    private Long eventoId;

    public AprovarSolicitacaoEventoResponseDTO() {
    }

    public AprovarSolicitacaoEventoResponseDTO(SolicitacaoEventoResponseDTO solicitacao, Long eventoId) {
        this.solicitacao = solicitacao;
        this.eventoId = eventoId;
    }

    public SolicitacaoEventoResponseDTO getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(SolicitacaoEventoResponseDTO solicitacao) {
        this.solicitacao = solicitacao;
    }

    public Long getEventoId() {
        return eventoId;
    }

    public void setEventoId(Long eventoId) {
        this.eventoId = eventoId;
    }
}
