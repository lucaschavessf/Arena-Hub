package com.arenahub.backend.dto;

import java.math.BigDecimal;

public class LoteResponseDTO {
    private Long id;
    private BigDecimal preco;
    private int quantidadeDisponivel;
    private String categoria; // CategoriaIngresso nome

    public LoteResponseDTO() {}

    public LoteResponseDTO(Long id, BigDecimal preco, int quantidadeDisponivel, String categoria) {
        this.id = id;
        this.preco = preco;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
