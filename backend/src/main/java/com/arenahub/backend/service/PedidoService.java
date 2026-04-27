package com.arenahub.backend.service;

import com.arenahub.backend.domain.Cliente;
import com.arenahub.backend.domain.Ingresso;
import com.arenahub.backend.domain.Lote;
import com.arenahub.backend.domain.Pedido;
import com.arenahub.backend.dto.IngressoResponseDTO;
import com.arenahub.backend.dto.PedidoRequestDTO;
import com.arenahub.backend.dto.PedidoResponseDTO;
import com.arenahub.backend.repository.ILoteRepository;
import com.arenahub.backend.repository.IPedidoRepository;
import com.arenahub.backend.repository.IUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PedidoService {

    private final IPedidoRepository _pedidoRepository;
    private final ILoteRepository _loteRepository;
    private final IUserRepository _userRepository;

    public PedidoService(IPedidoRepository _pedidoRepository,
                         ILoteRepository _loteRepository,
                         IUserRepository _userRepository) {
        this._pedidoRepository = _pedidoRepository;
        this._loteRepository = _loteRepository;
        this._userRepository = _userRepository;
    }

    @Transactional
    public PedidoResponseDTO realizarCompra(PedidoRequestDTO dto) {

        // 1. busca o cliente
        Cliente cliente = (Cliente) _userRepository.buscarPorId(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado: " + dto.getClienteId()));

        // 2. busca o lote
        Lote lote = _loteRepository.buscarPorId(dto.getLoteId())
                .orElseThrow(() -> new RuntimeException("Lote não encontrado: " + dto.getLoteId()));

        // 3. valida estoque
        if (lote.getQuantidadeDisponivel() < dto.getQuantidade()) {
            throw new RuntimeException(
                    "Quantidade indisponível. Disponível: " + lote.getQuantidadeDisponivel()
            );
        }

        // 4. cria o Pedido
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataCompra(LocalDateTime.now());
        pedido.setStatusPagamento(Pedido.StatusPagamento.PENDENTE);
        pedido.setValorTotal(
                lote.getPreco().multiply(BigDecimal.valueOf(dto.getQuantidade()))
        );

        // 5. gera os Ingressos
        List<Ingresso> ingressos = new ArrayList<>();
        for (int i = 0; i < dto.getQuantidade(); i++) {
            Ingresso ingresso = new Ingresso();
            ingresso.setPedido(pedido);
            ingresso.setLote(lote);
            ingresso.setCodigoValidacao(UUID.randomUUID().toString());
            ingressos.add(ingresso);
        }
        pedido.setIngressos(ingressos);

        // 6. atualiza o estoque do Lote
        lote.setQuantidadeDisponivel(
                lote.getQuantidadeDisponivel() - dto.getQuantidade()
        );

        // 7. salva tudo
        _pedidoRepository.inserir(pedido);
        _loteRepository.atualizar(lote);

        return toResponse(pedido);
    }

    @Transactional(readOnly = true)
    public PedidoResponseDTO buscar(Long id) {
        Pedido pedido = _pedidoRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado: " + id));
        return toResponse(pedido);
    }

    @Transactional(readOnly = true)
    public List<PedidoResponseDTO> buscarPorCliente(String clienteId) {
        return _pedidoRepository.buscarPorClienteId(clienteId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private PedidoResponseDTO toResponse(Pedido p) {
        PedidoResponseDTO dto = new PedidoResponseDTO();
        dto.setId(p.getId());
        dto.setDataCompra(p.getDataCompra());
        dto.setValorTotal(p.getValorTotal());
        dto.setStatusPagamento(p.getStatusPagamento().name());
        dto.setClienteId(p.getCliente().getId());

        List<IngressoResponseDTO> ingressosDTO = p.getIngressos()
                .stream()
                .map(this::toIngressoResponse)
                .toList();
        dto.setIngressos(ingressosDTO);

        return dto;
    }

    private IngressoResponseDTO toIngressoResponse(Ingresso i) {
        IngressoResponseDTO dto = new IngressoResponseDTO();
        dto.setId(i.getId());
        dto.setCodigoValidacao(i.getCodigoValidacao());
        dto.setLoteId(i.getLote().getId());
        dto.setEventoNome(i.getLote().getEvento().getNome());
        return dto;
    }
}