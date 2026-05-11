package com.arenahub.backend.repository;

import com.arenahub.backend.domain.solicitacao.SolicitacaoEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitacaoEventoRepository extends JpaRepository<SolicitacaoEvento, Long> {
}
