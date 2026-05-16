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
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.util.List;
import java.time.LocalDateTime;

import com.arenahub.backend.domain.evento.StatusEvento;

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

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    private List<Lote> lotes;

    @Enumerated(EnumType.STRING)
    private StatusEvento status;

    private LocalDateTime criadaEm;

    private LocalDateTime analisadaEm;

    private String motivoRejeicao;

    @ManyToOne
    @JoinColumn(name = "produtor_id")
    private Produtor produtor;

    public Evento() {
    }

    public Evento(String nome, String descricao, LocalDateTime dataInicio, LocalDateTime dataFim,
            Integer expectativaPublico, ClassificacaoIndicativa classificacaoIndicativa, CategoriaEvento categoria,
            Espaco espaco, Produtor produtor) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.expectativaPublico = expectativaPublico;
        this.classificacaoIndicativa = classificacaoIndicativa;
        this.categoria = categoria;
        this.espaco = espaco;
        this.status = StatusEvento.PENDENTE;
        this.criadaEm = LocalDateTime.now();
        this.produtor = produtor;
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

    public List<Lote> getLotes() {
        return lotes;
    }

    public void setLotes(List<Lote> lotes) {
        this.lotes = lotes;
    }

    public StatusEvento getStatus() {
        return status;
    }

    public void setStatus(StatusEvento status) {
        this.status = status;
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

    public String getMotivoRejeicao() {
        return motivoRejeicao;
    }

    public void setMotivoRejeicao(String motivoRejeicao) {
        this.motivoRejeicao = motivoRejeicao;
    }

    public Produtor getProdutor() {
        return produtor;
    }

    public void setProdutor(Produtor produtor) {
        this.produtor = produtor;
    }
}
