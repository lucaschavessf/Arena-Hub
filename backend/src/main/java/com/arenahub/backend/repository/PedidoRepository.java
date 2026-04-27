package com.arenahub.backend.repository;

import com.arenahub.backend.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository
        extends JpaRepository<Pedido,Long>, IPedidoRepository {

    List<Pedido> findByClienteId(String clienteId);

    default void inserir(Pedido pedido) { save(pedido); }
    default void atualizar(Pedido pedido) { save(pedido); }
    default void deletar(Long id) { deleteById(id); }
    default Optional<Pedido> buscarPorId(Long id) { return findById(id); }
    default List<Pedido> buscarPorClienteId(String clienteId) {
        return findByClienteId(clienteId);
    }
}

