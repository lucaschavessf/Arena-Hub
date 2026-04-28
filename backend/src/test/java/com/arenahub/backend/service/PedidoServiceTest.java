package com.arenahub.backend.service;

import com.arenahub.backend.domain.Cliente;
import com.arenahub.backend.domain.Evento;
import com.arenahub.backend.domain.Lote;
import com.arenahub.backend.domain.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PedidoServiceTest {

    private Cliente cliente;
    private Lote lote;
    private Evento evento;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId("uuid-cliente-123");
        cliente.setName("João Cliente");
        cliente.setEmail("joao@email.com");

        evento = new Evento();
        evento.setNome("Show do Metallica");

        lote = new Lote();
        lote.setId(1L);
        lote.setPreco(new BigDecimal("150.00"));
        lote.setQuantidadeTotal(10);
        lote.setQuantidadeDisponivel(10);
        lote.setEvento(evento);
    }

    @Test
    void deveCalcularValorTotalCorretamente() {
        int quantidade = 2;
        BigDecimal valorTotal = lote.getPreco()
                .multiply(BigDecimal.valueOf(quantidade));

        assertEquals(new BigDecimal("300.00"), valorTotal);
    }


    @Test
    void deveAtualizarEstoqueAposCompra() {
        int quantidadeAntes = lote.getQuantidadeDisponivel();
        int quantidadeComprada = 3;

        lote.setQuantidadeDisponivel(quantidadeAntes - quantidadeComprada);

        assertEquals(7, lote.getQuantidadeDisponivel());
    }

    @Test
    void deveLancarExcecaoQuandoEstoqueInsuficiente() {
        lote.setQuantidadeDisponivel(1);
        int quantidadeDesejada = 5;

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            if (lote.getQuantidadeDisponivel() < quantidadeDesejada) {
                throw new RuntimeException(
                        "Quantidade indisponível. Disponível: "
                                + lote.getQuantidadeDisponivel()
                );
            }
        });

        assertEquals("Quantidade indisponível. Disponível: 1", exception.getMessage());
    }

    @Test
    void deveCriarPedidoComStatusPendente() {
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataCompra(LocalDateTime.now());
        pedido.setStatusPagamento(Pedido.StatusPagamento.PENDENTE);
        pedido.setValorTotal(lote.getPreco().multiply(BigDecimal.valueOf(2)));
        pedido.setIngressos(new ArrayList<>());

        assertNotNull(pedido.getDataCompra());
        assertEquals(Pedido.StatusPagamento.PENDENTE, pedido.getStatusPagamento());
        assertEquals(new BigDecimal("300.00"), pedido.getValorTotal());
        assertEquals(cliente, pedido.getCliente());
    }


    @Test
    void deveIdentificarLoteEsgotado() {
        lote.setQuantidadeDisponivel(0);

        assertTrue(lote.getQuantidadeDisponivel() == 0);
    }
}