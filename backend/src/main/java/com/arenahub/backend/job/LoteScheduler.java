package com.arenahub.backend.job;

import com.arenahub.backend.domain.Lote;
import com.arenahub.backend.domain.StatusLote;
import com.arenahub.backend.repository.LoteRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class LoteScheduler {

    private final LoteRepository loteRepository;

    public LoteScheduler(LoteRepository loteRepository) {
        this.loteRepository = loteRepository;
    }

    @Scheduled(cron = "0 * * * * *")
    public void atualizarStatusLotes() {
        LocalDateTime agora = LocalDateTime.now();

        List<Lote> todosLotes = loteRepository.findAll();

        todosLotes.stream()
                .filter(l -> l.getStatus() == StatusLote.ATIVO)
                .filter(l -> l.getDataFim() != null && l.getDataFim().isBefore(agora))
                .forEach(l -> {
                    l.setStatus(StatusLote.ENCERRADO);
                    loteRepository.save(l);
                });

        todosLotes.stream()
                .filter(l -> l.getStatus() == StatusLote.AGENDADO)
                .filter(l -> l.getDataInicio() != null && (l.getDataInicio().isBefore(agora) || l.getDataInicio().isEqual(agora)))
                .forEach(novoAtivo -> {
                    List<Lote> eventoLotes = loteRepository.findByEventoId(novoAtivo.getEvento().getId());
                    eventoLotes.stream()
                            .filter(l -> l.getStatus() == StatusLote.ATIVO)
                            .filter(l -> l.getNome() != null && !l.getNome().equals(novoAtivo.getNome()))
                            .forEach(ativo -> {
                                ativo.setStatus(StatusLote.PAUSADO);
                                loteRepository.save(ativo);
                            });

                    novoAtivo.setStatus(StatusLote.ATIVO);
                    loteRepository.save(novoAtivo);
                });
    }
}
