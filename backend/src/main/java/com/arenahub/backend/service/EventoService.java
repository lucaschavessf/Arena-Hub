package com.arenahub.backend.service;

import com.arenahub.backend.domain.Evento;
import com.arenahub.backend.domain.categoria.CategoriaEvento;
import com.arenahub.backend.domain.espaco.Espaco;
import com.arenahub.backend.dto.EventoRequestDTO;
import com.arenahub.backend.repository.CategoriaEventoRepository;
import com.arenahub.backend.repository.EspacoRepository;
import com.arenahub.backend.repository.EventoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;
import com.arenahub.backend.dto.EventoDetalhesDTO;
import com.arenahub.backend.dto.LoteResponseDTO;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final CategoriaEventoRepository categoriaRepository;
    private final EspacoRepository espacoRepository;

    public EventoService(EventoRepository eventoRepository, CategoriaEventoRepository categoriaRepository,
            EspacoRepository espacoRepository) {
        this.eventoRepository = eventoRepository;
        this.categoriaRepository = categoriaRepository;
        this.espacoRepository = espacoRepository;
    }

    public List<Evento> listarEventosFiltrados(String categoria, LocalDateTime data) {
        if (categoria != null && data != null) {
            return eventoRepository.findByCategoriaAndData(categoria, data);
        }
        return eventoRepository.findAll();
    }

    public Optional<Evento> listarEventoPorId(Long id) {
        return eventoRepository.findById(id);
    }

    public Optional<EventoDetalhesDTO> buscarDetalhesPorId(Long id) {
        return eventoRepository.findById(id).map(evento -> {
            List<LoteResponseDTO> lotesDTO = (evento.getLotes() == null) ? List.of() : evento.getLotes().stream()
                    .map(lote -> new LoteResponseDTO(
                            lote.getId(),
                            lote.getPreco(),
                            lote.getQuantidadeDisponivel(),
                            lote.getCategoria() != null ? lote.getCategoria().getNome() : null))
                    .collect(Collectors.toList());

            return new EventoDetalhesDTO(
                    evento.getId(),
                    evento.getNome(),
                    evento.getDescricao(),
                    evento.getDataInicio(),
                    evento.getDataFim(),
                    evento.getExpectativaPublico(),
                    evento.getClassificacaoIndicativa(),
                    evento.getCategoria() != null ? evento.getCategoria().getNome() : null,
                    evento.getEspaco() != null ? evento.getEspaco().getNome() : null,
                    lotesDTO
            );
        });
    }

    public Evento cadastrarEvento(EventoRequestDTO data) {
        CategoriaEvento categoria = categoriaRepository.findById(data.categoriaId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Categoria não encontrada com id: " + data.categoriaId()));

        Espaco espaco = espacoRepository.findById(data.espacoId())
                .orElseThrow(() -> new EntityNotFoundException("Espaço não encontrado com id: " + data.espacoId()));

        Evento novoEvento = new Evento(
                data.nome(),
                data.descricao(),
                data.dataInicio(),
                data.dataFim(),
                data.expectativaPublico(),
                data.classificacaoIndicativa(),
                categoria,
                espaco);
        return eventoRepository.save(novoEvento);
    }

    public Evento atualizarEvento(Long id, EventoRequestDTO data) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Evento não encontrado com id: " + id));

        CategoriaEvento categoria = categoriaRepository.findById(data.categoriaId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Categoria não encontrada com id: " + data.categoriaId()));

        Espaco espaco = espacoRepository.findById(data.espacoId())
                .orElseThrow(() -> new EntityNotFoundException("Espaço não encontrado com id: " + data.espacoId()));

        evento.setNome(data.nome());
        evento.setDescricao(data.descricao());
        evento.setDataInicio(data.dataInicio());
        evento.setDataFim(data.dataFim());
        evento.setExpectativaPublico(data.expectativaPublico());
        evento.setClassificacaoIndicativa(data.classificacaoIndicativa());
        evento.setCategoria(categoria);
        evento.setEspaco(espaco);

        return eventoRepository.save(evento);
    }

    public void removerEvento(Long id) {
        if (!eventoRepository.existsById(id)) {
            throw new EntityNotFoundException("Evento não encontrado com id: " + id);
        }
        eventoRepository.deleteById(id);
    }
}
