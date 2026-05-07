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
