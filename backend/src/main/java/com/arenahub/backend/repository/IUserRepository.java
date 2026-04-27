package  com.arenahub.backend.repository;

import com.arenahub.backend.domain.User;

import java.util.Optional;

public interface IUserRepository {

    void inserir(User usuario);

    void atualizar(User usuario);

    void deletar(String id);

    Optional<User> buscarPorId(String id);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}