package com.arenahub.backend.dto;

import com.arenahub.backend.domain.evento.ClassificacaoIndicativa;
import java.time.LocalDateTime;
import java.util.List;

public class EventoDetalhesDTO {
    private Long id;
    private String nome;
    private String descricao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private Integer expectativaPublico;
    private ClassificacaoIndicativa classificacaoIndicativa;
    private String categoria;
    private String espaco;
    private List<LoteResponseDTO> lotes;

    public EventoDetalhesDTO() {}

    public EventoDetalhesDTO(Long id, String nome, String descricao, LocalDateTime dataInicio, LocalDateTime dataFim,
                             Integer expectativaPublico, ClassificacaoIndicativa classificacaoIndicativa,
                             String categoria, String espaco, List<LoteResponseDTO> lotes) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.expectativaPublico = expectativaPublico;
        this.classificacaoIndicativa = classificacaoIndicativa;
        this.categoria = categoria;
        this.espaco = espaco;
        this.lotes = lotes;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public LocalDateTime getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDateTime dataInicio) { this.dataInicio = dataInicio; }
    
    public LocalDateTime getDataFim() { return dataFim; }
    public void setDataFim(LocalDateTime dataFim) { this.dataFim = dataFim; }
    
    public Integer getExpectativaPublico() { return expectativaPublico; }
    public void setExpectativaPublico(Integer expectativaPublico) { this.expectativaPublico = expectativaPublico; }
    
    public ClassificacaoIndicativa getClassificacaoIndicativa() { return classificacaoIndicativa; }
    public void setClassificacaoIndicativa(ClassificacaoIndicativa classificacaoIndicativa) { this.classificacaoIndicativa = classificacaoIndicativa; }
    
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    
    public String getEspaco() { return espaco; }
    public void setEspaco(String espaco) { this.espaco = espaco; }
    
    public List<LoteResponseDTO> getLotes() { return lotes; }
    public void setLotes(List<LoteResponseDTO> lotes) { this.lotes = lotes; }
}
