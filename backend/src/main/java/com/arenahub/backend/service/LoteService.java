package com.arenahub.backend.service;

import com.arenahub.backend.domain.CategoriaIngresso;
import com.arenahub.backend.domain.Evento;
import com.arenahub.backend.domain.Lote;
import com.arenahub.backend.domain.StatusLote;
import com.arenahub.backend.dto.LoteBlocoRequestDTO;
import com.arenahub.backend.dto.LoteBlocoResponseDTO;
import com.arenahub.backend.dto.LoteItemRequestDTO;
import com.arenahub.backend.dto.LoteItemResponseDTO;
import com.arenahub.backend.repository.CategoriaIngressoRepository;
import com.arenahub.backend.repository.EventoRepository;
import com.arenahub.backend.repository.LoteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LoteService {

    private final LoteRepository loteRepository;
    private final EventoRepository eventoRepository;
    private final CategoriaIngressoRepository categoriaRepository;

    public LoteService(LoteRepository loteRepository, EventoRepository eventoRepository, CategoriaIngressoRepository categoriaRepository) {
        this.loteRepository = loteRepository;
        this.eventoRepository = eventoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<LoteBlocoResponseDTO> listarLotesDoEvento(Long eventoId) {
        List<Lote> lotes = loteRepository.findByEventoId(eventoId);

        Map<String, List<Lote>> lotesPorNome = lotes.stream()
                .collect(Collectors.groupingBy(lote -> lote.getNome() != null ? lote.getNome() : "Sem Nome"));

        List<LoteBlocoResponseDTO> blocos = new ArrayList<>();

        for (Map.Entry<String, List<Lote>> entry : lotesPorNome.entrySet()) {
            String nome = entry.getKey();
            List<Lote> lotesDoBloco = entry.getValue();

            String status = lotesDoBloco.get(0).getStatus() != null ? lotesDoBloco.get(0).getStatus().name() : "";
            java.time.LocalDateTime dataInicio = lotesDoBloco.get(0).getDataInicio();
            java.time.LocalDateTime dataFim = lotesDoBloco.get(0).getDataFim();

            List<LoteItemResponseDTO> itens = lotesDoBloco.stream().map(l -> new LoteItemResponseDTO(
                    l.getId(),
                    l.getCategoria().getId(),
                    l.getCategoria().getNome(),
                    l.getPreco(),
                    l.getQuantidadeTotal(),
                    l.getQuantidadeTotal() - l.getQuantidadeDisponivel(),
                    l.getQuantidadeDisponivel()
            )).collect(Collectors.toList());

            blocos.add(new LoteBlocoResponseDTO(nome, status, dataInicio, dataFim, itens));
        }

        return blocos;
    }

    public void salvarLoteBloco(Long eventoId, LoteBlocoRequestDTO dto) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        int capacidadeMaxima = evento.getEspaco() != null ? evento.getEspaco().getCapacidade() : 0;

        List<Lote> lotesExistentes = loteRepository.findByEventoId(eventoId);
        
        // Remove existing lotes with the SAME NAME since we will replace/update them
        List<Lote> lotesExistentesOutros = lotesExistentes.stream()
                .filter(l -> !dto.nome().equals(l.getNome()))
                .collect(Collectors.toList());

        int quantidadeExistente = lotesExistentesOutros.stream().mapToInt(Lote::getQuantidadeTotal).sum();
        int quantidadeNovos = dto.itens().stream().mapToInt(LoteItemRequestDTO::quantidadeTotal).sum();

        if (quantidadeExistente + quantidadeNovos > capacidadeMaxima) {
            throw new RuntimeException("A soma da quantidade total ultrapassa a capacidade máxima do espaço!");
        }

        if (dto.dataInicio() != null && dto.dataFim() != null) {
            if (!dto.dataFim().isAfter(dto.dataInicio())) {
                throw new RuntimeException("A data de término deve ser posterior à data de início.");
            }
        }

        for (Lote existente : lotesExistentesOutros) {
            if (existente.getDataInicio() != null && existente.getDataFim() != null &&
                dto.dataInicio() != null && dto.dataFim() != null) {
                if (dto.dataInicio().isBefore(existente.getDataFim()) && dto.dataFim().isAfter(existente.getDataInicio())) {
                    throw new RuntimeException("Não pode haver sobreposição de datas entre lotes do mesmo evento.");
                }
            }
        }

        StatusLote novoStatus = dto.status() != null ? StatusLote.valueOf(dto.status().toUpperCase()) : StatusLote.AGENDADO;

        if (novoStatus == StatusLote.ATIVO) {
            for (Lote existente : lotesExistentesOutros) {
                if (existente.getStatus() == StatusLote.ATIVO) {
                    existente.setStatus(StatusLote.PAUSADO);
                    loteRepository.save(existente);
                }
            }
        }

        // Delete old lots of this block
        List<Lote> lotesAntigosDesteBloco = lotesExistentes.stream()
                .filter(l -> dto.nome().equals(l.getNome()))
                .collect(Collectors.toList());
        loteRepository.deleteAll(lotesAntigosDesteBloco);

        for (LoteItemRequestDTO item : dto.itens()) {
            CategoriaIngresso categoria = categoriaRepository.findById(item.categoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

            Lote lote = new Lote();
            lote.setNome(dto.nome());
            lote.setStatus(novoStatus);
            lote.setDataInicio(dto.dataInicio());
            lote.setDataFim(dto.dataFim());
            lote.setPreco(item.preco());
            lote.setQuantidadeTotal(item.quantidadeTotal());
            lote.setQuantidadeDisponivel(item.quantidadeTotal()); // Starts with all available
            lote.setEvento(evento);
            lote.setCategoria(categoria);

            loteRepository.save(lote);
        }
    }

    public void alterarStatusLoteBloco(Long eventoId, String nome, String novoStatus) {
        List<Lote> lotesExistentes = loteRepository.findByEventoId(eventoId);
        List<Lote> lotesDesteBloco = lotesExistentes.stream()
                .filter(l -> nome.equals(l.getNome()))
                .collect(Collectors.toList());

        StatusLote novoStatusEnum = StatusLote.valueOf(novoStatus.toUpperCase());
        if (novoStatusEnum == StatusLote.ATIVO) {
            List<Lote> lotesExistentesOutros = lotesExistentes.stream()
                    .filter(l -> !nome.equals(l.getNome()))
                    .collect(Collectors.toList());
            for (Lote existente : lotesExistentesOutros) {
                if (existente.getStatus() == StatusLote.ATIVO) {
                    existente.setStatus(StatusLote.PAUSADO);
                    loteRepository.save(existente);
                }
            }
        }

        for (Lote lote : lotesDesteBloco) {
            lote.setStatus(novoStatusEnum);
            loteRepository.save(lote);
        }
    }

    public void excluirLoteBloco(Long eventoId, String nome) {
        List<Lote> lotesExistentes = loteRepository.findByEventoId(eventoId);
        List<Lote> lotesDesteBloco = lotesExistentes.stream()
                .filter(l -> nome.equals(l.getNome()))
                .collect(Collectors.toList());
        loteRepository.deleteAll(lotesDesteBloco);
    }
}
