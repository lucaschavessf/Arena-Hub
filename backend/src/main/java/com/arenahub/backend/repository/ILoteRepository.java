package com.arenahub.backend.repository;

import com.arenahub.backend.domain.Lote;
import java.util.Optional;

public interface ILoteRepository {

    void inserir(Lote lote);
    void atualizar(Lote lote);
    Optional<Lote> buscarPorId(Long id);
}