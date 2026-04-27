package com.arenahub.backend.repository;

import com.arenahub.backend.domain.Lote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Long>, ILoteRepository {

    default void inserir(Lote lote) { save(lote); }
    default void atualizar(Lote lote) { save(lote); }
    default Optional<Lote> buscarPorId(Long id) { return findById(id); }
}