package com.arenahub.backend.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "ingressos")
public class Ingresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_validacao", nullable = false, unique = true)
    private String codigoValidacao = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "lote_id", nullable = false)
    private Lote lote;

    public Ingresso() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigoValidacao() { return codigoValidacao; }
    public void setCodigoValidacao(String codigoValidacao) { this.codigoValidacao = codigoValidacao; }

    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

    public Lote getLote() { return lote; }
    public void setLote(Lote lote) { this.lote = lote; }
}