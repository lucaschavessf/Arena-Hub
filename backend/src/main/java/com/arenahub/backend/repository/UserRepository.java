package com.arenahub.backend.repository;

import com.arenahub.backend.domain.usuario.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String>, IUserRepository
{

    default void inserir(User usuario){
        save(usuario);
    }

    default void atualizar(User usuario){
        save(usuario);
    }

    default void deletar(String id){
        deleteById(id);
    }

    default Optional<User> buscarPorId(String id){
        return findById(id);
    }
}