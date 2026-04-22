package com.arenahub.backend.controller;

import com.arenahub.backend.domain.evento.Evento;
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

@RestController
@RequestMapping("/eventos")
@CrossOrigin(origins = "*")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    public ResponseEntity<List<Evento>> getEventos(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) LocalDateTime data) {
        List<Evento> eventos = eventoService.listarEventosFiltrados(categoria, data);
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Long id) {
        Optional<Evento> evento = eventoService.listarEventoPorId(id);
        return evento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Evento> createEvento(@Valid @RequestBody EventoRequestDTO data, UriComponentsBuilder uriBuilder) {
        Evento evento = eventoService.cadastrarEvento(data);
        URI uri = uriBuilder.path("/eventos/{id}").buildAndExpand(evento.getId()).toUri();
        return ResponseEntity.created(uri).body(evento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> updateEvento(@PathVariable Long id, @Valid @RequestBody EventoRequestDTO data) {
        try {
            Evento eventoAtualizado = eventoService.atualizarEvento(id, data);
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
}
