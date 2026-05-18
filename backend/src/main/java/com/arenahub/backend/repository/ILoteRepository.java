package com.arenahub.backend.repository;

import com.arenahub.backend.domain.Lote;
import java.util.Optional;
import java.util.List;

public interface ILoteRepository {

    void inserir(Lote lote);
    void atualizar(Lote lote);
    Optional<Lote> buscarPorId(Long id);
    List<Lote> findByEventoId(Long eventoId);
}