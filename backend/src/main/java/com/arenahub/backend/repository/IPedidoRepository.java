package  com.arenahub.backend.repository;


import com.arenahub.backend.domain.Pedido;
import java.util.List;
import java.util.Optional;

public interface IPedidoRepository {

    void inserir(Pedido pedido);
    void atualizar(Pedido pedido);
    void deletar(Long id);
    Optional<Pedido> buscarPorId(Long id);
    List<Pedido> buscarPorClienteId(String clienteId);
}

