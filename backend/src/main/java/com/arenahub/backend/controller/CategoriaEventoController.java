package com.arenahub.backend.controller;

import com.arenahub.backend.domain.categoria.CategoriaEvento;
import com.arenahub.backend.service.CategoriaEventoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria-evento")
@CrossOrigin(origins = "*")
public class CategoriaEventoController {

    private final CategoriaEventoService service;

    public CategoriaEventoController(CategoriaEventoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaEvento>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaEvento> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoriaEvento> create(@RequestBody CategoriaEvento categoriaEvento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(categoriaEvento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaEvento> update(@PathVariable Long id, @RequestBody CategoriaEvento categoriaEvento) {
        try {
            return ResponseEntity.ok(service.update(id, categoriaEvento));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
