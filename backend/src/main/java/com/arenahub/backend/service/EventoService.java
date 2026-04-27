package com.arenahub.backend.service;

import com.arenahub.backend.domain.Evento;
import com.arenahub.backend.dto.EventoRequestDTO;
import com.arenahub.backend.repository.EventoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public List<Evento> listarEventosFiltrados(String categoria, LocalDateTime data) {
        if (categoria != null && data != null) {
            return eventoRepository.findByCategoriaAndData(categoria, data);
        } else if (categoria != null) {
            // Se precisar de um método find by Categoria, adicione ao repositório
            // return eventoRepository.findByCategoria(categoria);
        } else if (data != null) {
            // Se precisar de um método find by Data, adicione ao repositório
            // return eventoRepository.findByDataInicio(data);
        }
        return eventoRepository.findAll();
    }
    
    public Optional<Evento> listarEventoPorId(Long id) {
        return eventoRepository.findById(id);
    }

    public Evento cadastrarEvento(EventoRequestDTO data) {
        // TODO: Validar se evento.ExpectativaPublico <= espaco.CapacidadeMaxima
        Evento novoEvento = new Evento(
                data.nome(),
                data.descricao(),
                data.dataInicio(),
                data.dataFim(),
                data.expectativaPublico(),
                data.categoria(),
                data.espacoId()
        );
        return eventoRepository.save(novoEvento);
    }

    public Evento atualizarEvento(Long id, EventoRequestDTO data) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Evento não encontrado com id: " + id));

        evento.setNome(data.nome());
        evento.setDescricao(data.descricao());
        evento.setDataInicio(data.dataInicio());
        evento.setDataFim(data.dataFim());
        evento.setExpectativaPublico(data.expectativaPublico());
        evento.setCategoria(data.categoria());
        evento.setEspacoId(data.espacoId());

        return eventoRepository.save(evento);
    }



    public void removerEvento(Long id) {
        if (!eventoRepository.existsById(id)) {
            throw new EntityNotFoundException("Evento não encontrado com id: " + id);
        }
        eventoRepository.deleteById(id);
    }
}
