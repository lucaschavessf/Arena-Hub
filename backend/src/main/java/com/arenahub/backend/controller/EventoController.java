package com.arenahub.backend.controller;

import com.arenahub.backend.domain.Evento;
import com.arenahub.backend.dto.EventoRequestDTO;
import com.arenahub.backend.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.arenahub.backend.dto.EventoDetalhesDTO;
import com.arenahub.backend.dto.EventoResumoDTO;

import com.arenahub.backend.dto.EventoResponseDTO;
import java.util.Map;
import org.springframework.format.annotation.DateTimeFormat;

@RestController
@RequestMapping("/api/eventos")
@CrossOrigin(origins = "*")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    public ResponseEntity<List<EventoResponseDTO>> getEventos(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) LocalDateTime data) {
        List<EventoResponseDTO> eventos = eventoService.listarEventosFiltrados(categoria, data);
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/meus-eventos")
    public ResponseEntity<List<EventoResumoDTO>> getMeusEventos() {
        List<EventoResumoDTO> meusEventos = eventoService.listarMeusEventosAprovados();
        return ResponseEntity.ok(meusEventos);
    }

    @GetMapping("/aprovados")
    public ResponseEntity<List<EventoResponseDTO>> getEventosAprovados() {
        List<EventoResponseDTO> eventos = eventoService.listarEventosAprovados();
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/produtor")
    public ResponseEntity<List<EventoResponseDTO>> getEventosDoProdutor() {
        List<EventoResponseDTO> eventos = eventoService.listarEventosDoProdutor();
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoDetalhesDTO> getEventoById(@PathVariable Long id) {
        Optional<EventoDetalhesDTO> evento = eventoService.buscarDetalhesPorId(id);
        return evento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/validar-disponibilidade")
    public ResponseEntity<Map<String, Object>> validarDisponibilidade(
            @RequestParam Long espacoId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim) {
        boolean disponivel = eventoService.validarDisponibilidade(espacoId, dataInicio, dataFim);
        String mensagem = disponivel ? "Espaço disponível" : "Conflito de agenda: O espaço já possui um evento marcado nesse período.";
        return ResponseEntity.ok(Map.of(
                "disponivel", disponivel,
                "mensagem", mensagem
        ));
    }

    @PostMapping
    public ResponseEntity<EventoResponseDTO> createEvento(@Valid @RequestBody EventoRequestDTO data, UriComponentsBuilder uriBuilder) {
        EventoResponseDTO evento = eventoService.cadastrarEvento(data);
        URI uri = uriBuilder.path("/api/eventos/{id}").buildAndExpand(evento.id()).toUri();
        return ResponseEntity.created(uri).body(evento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoResponseDTO> updateEvento(@PathVariable Long id, @Valid @RequestBody EventoRequestDTO data) {
        try {
            EventoResponseDTO eventoAtualizado = eventoService.atualizarEvento(id, data);
            return ResponseEntity.ok(eventoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Long id) {
        try {
            eventoService.removerEvento(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/aprovar")
    public ResponseEntity<EventoResponseDTO> aprovarEvento(@PathVariable Long id) {
        try {
            EventoResponseDTO evento = eventoService.analisarEvento(id, true, null);
            return ResponseEntity.ok(evento);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    public record RejeitarRequest(String motivo) {}

    @PostMapping("/{id}/rejeitar")
    public ResponseEntity<EventoResponseDTO> rejeitarEvento(@PathVariable Long id, @RequestBody RejeitarRequest request) {
        try {
            EventoResponseDTO evento = eventoService.analisarEvento(id, false, request.motivo());
            return ResponseEntity.ok(evento);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
