package  com.arenahub.backend.repository;

import com.arenahub.backend.domain.usuario.User;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository {

    void inserir(User usuario);

    void atualizar(User usuario);

    void deletar(String id);

    Optional<User> buscarPorId(String id);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}