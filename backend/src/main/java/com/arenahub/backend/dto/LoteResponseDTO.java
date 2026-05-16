package com.arenahub.backend.dto;

import java.math.BigDecimal;

public class LoteResponseDTO {
    private Long id;
    private String nome;
    private BigDecimal preco;
    private int quantidadeTotal;
    private int vendidos;
    private int disponiveis;
    private String categoria;
    private String status;

    public LoteResponseDTO() {}

    public LoteResponseDTO(Long id, String nome, BigDecimal preco, int quantidadeTotal, int vendidos, int disponiveis, String categoria, String status) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidadeTotal = quantidadeTotal;
        this.vendidos = vendidos;
        this.disponiveis = disponiveis;
        this.categoria = categoria;
        this.status = status;
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

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getQuantidadeTotal() { return quantidadeTotal; }
    public void setQuantidadeTotal(int quantidadeTotal) { this.quantidadeTotal = quantidadeTotal; }

    public int getVendidos() { return vendidos; }
    public void setVendidos(int vendidos) { this.vendidos = vendidos; }

    public int getDisponiveis() { return disponiveis; }
    public void setDisponiveis(int disponiveis) { this.disponiveis = disponiveis; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
